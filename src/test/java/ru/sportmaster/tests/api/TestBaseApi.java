package ru.sportmaster.tests.api;

import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.sportmaster.config.Project;
import ru.sportmaster.tests.TestBase;

import static ru.sportmaster.helpers.AllureRestAssuredFilter.withCustomTemplates;

@ExtendWith({AllureJunit5.class})
public class TestBaseApi extends TestBase {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Project.config.baseUrl();
        RestAssured.filters(withCustomTemplates());
    }
}
