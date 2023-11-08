package duzo.client;

import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentClient extends Client {
    private String studentCode = "";
    private String dateOfBirth = "";

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
}
