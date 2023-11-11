package api.duzo.client;

import api.duzo.announcement.Announcement;
import api.duzo.homework.Homework;
import api.duzo.point.BehaviourPoint;
import api.duzo.student.StudentInformation;
import api.duzo.timetable.DayTimetable;
import com.google.gson.*;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class Client {
    protected Gson gson = new Gson();
    protected HttpClient httpClient = HttpClient.newHttpClient();
    protected HttpCookie authCookies;
    public int studentId = 0;
    public String sessionId = "";
    public Date lastPing;
    public String CLASSCHARTS_URL = "https://www.classcharts.com";
    protected String API_BASE = "";
    protected int PING_INTERVAL = 60 * 3 * 1000; // 3 minutes
    public Client(String apiBase) {
        this.API_BASE = apiBase;
    }

    public static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static long millisBetweenDates(Date date1, Date date2) {
        return Math.abs(date2.getTime() - date1.getTime());
    }

    public static JsonObject responseToJson(HttpResponse<String> response) {
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    public static String convertDOBToDashFormat(String dob) {
        String day = (String) dob.subSequence(0,2);
        String month = (String) dob.subSequence(3,5);
        String year = (String) dob.subSequence(5,10);
        String newDob = (year + "-" + month + "-" + day).substring(1);
        return newDob;
    }

    protected static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }
    public abstract void login() throws IOException, InterruptedException;

    public void updateInformation() throws IOException, InterruptedException {
        HttpResponse<String> response = this.makeApiRequest(API_BASE + "/ping",false,HttpRequest.BodyPublishers.noBody());

        JsonObject responseJson = JsonParser.parseString(response.body()).getAsJsonObject();
        this.getNewSessionId(responseJson);
        this.updateStudentId(responseJson);
    }

    private void getNewSessionId(JsonObject pingJson) {
        if (!pingJson.has("meta")) return;
        if (!pingJson.get("meta").getAsJsonObject().has("session_id")) return;

        System.out.println(this.sessionId);

        this.sessionId = pingJson.get("meta").getAsJsonObject()
                .get("session_id").getAsString();
        this.lastPing = currentDate();
    }
    private void updateStudentId(JsonObject pingJson) {
        if (!pingJson.has("data")) return;

        JsonObject data = pingJson.get("data").getAsJsonObject();
        if (!data.has("user")) return;
        if (!data.get("user").getAsJsonObject().has("id")) return;

        this.studentId = data.get("user").getAsJsonObject()
                .get("id").getAsInt();
    }

    public HttpResponse<String> makeApiRequest(
            String path,
            boolean updateSession,
            HttpRequest.BodyPublisher body
    ) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .POST(body)
                .header("Authorization","Basic " + this.sessionId)
                .header("Cookie",this.authCookies.toString())
                .build();

        if (updateSession && (millisBetweenDates(this.lastPing, currentDate()) > PING_INTERVAL)) {
           this.updateInformation();
        }

        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getApiResponse(
            String path,
            boolean updateSession
    ) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .header("Authorization","Basic " + this.sessionId)
                .header("Cookie",this.authCookies.toString())
                .build();

        if (updateSession && (millisBetweenDates(this.lastPing, currentDate()) > PING_INTERVAL)) {
            this.updateInformation();
        }

        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public JsonArray getJsonResponse(String path, String fields) throws IOException, InterruptedException {
        HttpResponse<String> response = this.getApiResponse(API_BASE + "/" + path + "/" + this.studentId + fields, true);
        JsonArray data = responseToJson(response).getAsJsonArray("data");
        return data;
    }
    public JsonArray getJsonResponse(String path) throws IOException, InterruptedException {
        return getJsonResponse(path,"");
    }

    protected JsonArray getActivityJson() throws IOException, InterruptedException {
        return getJsonResponse("activity").getAsJsonArray();
    }
    public List<BehaviourPoint> getActivity() throws IOException, InterruptedException {
        List<BehaviourPoint> list = new ArrayList<>();

        for (JsonElement element : this.getActivityJson()) {
            list.add(BehaviourPoint.fromJson(element.getAsJsonObject()));
        }

        return list;
    }
    protected JsonArray getBehaviourJson() throws IOException, InterruptedException {
        return getJsonResponse("behaviour").getAsJsonArray();
    }
    public List<BehaviourPoint> getBehaviour() throws IOException, InterruptedException {
        List<BehaviourPoint> list = new ArrayList<>();

        for (JsonElement element : this.getBehaviourJson()) {
            list.add(BehaviourPoint.fromJson(element.getAsJsonObject()));
        }

        return list;
    }
    protected JsonArray getHomeworkJson() throws IOException, InterruptedException {
        return getJsonResponse("homeworks").getAsJsonArray();
    }
    public List<Homework> getHomeworks() throws IOException, InterruptedException {
        List<Homework> list = new ArrayList<>();

        for (JsonElement element : this.getHomeworkJson()) {
            list.add(Homework.fromJson(element.getAsJsonObject()));
        }

        return list;
    }

    protected JsonArray getTimetableJson(String date) throws IOException, InterruptedException {
        return getJsonResponse("timetable","?date=" + date).getAsJsonArray();
    }

    /**
     *
     * @param date Must be in format yyyy-MM-dd eg 2023-11-08
     * @return
     */
    public DayTimetable getTimetable(String date) throws IOException, InterruptedException {
        return DayTimetable.fromJson(getTimetableJson(date));
    }
    @Deprecated
    /**
     * Incomplete, cannot test.
     */
    protected JsonArray getBadgesJson() throws IOException, InterruptedException {
        return getJsonResponse("eventbadges");
    }
    protected JsonArray getAnnouncementsJson() throws IOException, InterruptedException {
        return getJsonResponse("announcements");
    }
    public List<Announcement> getAnnouncements() throws IOException, InterruptedException {
        List<Announcement> list = new ArrayList<>();

        for (JsonElement element : this.getAnnouncementsJson()) {
            list.add(Announcement.fromJson(element.getAsJsonObject()));
        }

        return list;
    }
    @Deprecated
    /**
     * Incomplete, cannot test.
     */
    protected JsonArray getDetentionsJson() throws IOException, InterruptedException {
        return getJsonResponse("detentions");
    }
    protected JsonObject getStudentInfoJson() throws IOException, InterruptedException {
        HttpResponse<String> response = this.makeApiRequest(API_BASE + "/ping",false,HttpRequest.BodyPublishers.noBody());

        return responseToJson(response).getAsJsonObject("data").getAsJsonObject("user");
    }
    public StudentInformation getStudentInfo() throws IOException, InterruptedException {
        return StudentInformation.fromJson(getStudentInfoJson());
    }
}
