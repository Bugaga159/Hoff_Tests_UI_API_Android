package ru.sportmaster.helpers;

import ru.sportmaster.config.Project;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String
            browserstackLogin = Project.config.user(),
            browserstackPassword = Project.config.key();

    public static String videoUrl(String sessionId) {
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(browserstackLogin, browserstackPassword)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
