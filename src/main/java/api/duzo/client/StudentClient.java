package api.duzo.client;

import api.duzo.reward.Reward;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentClient extends Client {
    private String studentCode = "";
    private String dateOfBirth = "";

    /**
     * Creates a client
     * @param studentCode
     * @param dateOfBirth in format DD/MM/YYYY
     */
    public StudentClient(String studentCode, String dateOfBirth) {
        super("https://www.classcharts.com/apiv2student");
        this.studentCode = studentCode;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public void login() throws IOException, InterruptedException {
        if (this.studentCode == "" || this.studentCode == null) throw new Error("Student Code not provided");

        Map<String, String> formData = new HashMap<>();
        formData.put("_method","POST");
        formData.put("code",this.studentCode.toUpperCase());
        formData.put("dob",this.dateOfBirth);
        formData.put("remember_me", "1");
        formData.put("recaptcha-token", "no-token-available");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CLASSCHARTS_URL + "/student/login"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (!response.headers().firstValue("set-cookie").isPresent()) {
            return;
        }

        List<String> cookiesHeader = response.headers().map().get("set-cookie");
        String cookie = cookiesHeader.get(1);
        HttpCookie httpCookie = HttpCookie.parse(cookie).get(0);
        this.authCookies = httpCookie;
        this.sessionId = JsonParser.parseString(URLDecoder.decode(httpCookie.getValue())).getAsJsonObject().get("session_id").getAsString();
        this.lastPing = currentDate();
        this.updateInformation();
    }

    protected JsonArray getRewardsJson() throws IOException, InterruptedException {
        return getJsonResponse("rewards").getAsJsonArray();
    }
    public List<Reward> getRewards() throws IOException, InterruptedException {
        List<Reward> list = new ArrayList<>();

        for (JsonElement element : this.getRewardsJson()) {
            list.add(Reward.fromJson(element.getAsJsonObject()));
        }

        return list;
    }

    /**
     * Refuse to test, dont wanna waste my points.
     * @param itemId
     * @throws IOException
     * @throws InterruptedException
     */
    public void purchaseReward(int itemId) throws IOException, InterruptedException {
        this.makeApiRequest(
                this.API_BASE + "/purchase/" + itemId,
                true,
                HttpRequest.BodyPublishers.ofString("pupil_id=" + this.studentId)
        );
    }
    public void purchaseReward(Reward reward) throws IOException, InterruptedException {
        this.purchaseReward(reward.getId());
    }

    // @TODO doesnt appear to work
    public String getStudentCode() throws IOException, InterruptedException {
        System.out.println(convertDOBToDashFormat(this.dateOfBirth));
        JsonObject date = new JsonObject();
        date.addProperty("date",convertDOBToDashFormat(this.dateOfBirth));
        HttpResponse<String> response = this.makeApiRequest(
                this.API_BASE + "/getcode",
                true,
                HttpRequest.BodyPublishers.ofString(date.toString())
        );
        System.out.println(date.toString());
        return response.body();
    }
}
