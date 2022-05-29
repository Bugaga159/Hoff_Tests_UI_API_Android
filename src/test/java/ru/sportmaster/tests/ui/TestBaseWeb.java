package ru.sportmaster.tests.ui;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.sportmaster.config.Project;
import ru.sportmaster.drivers.BrowserDriver;
import ru.sportmaster.helpers.AllureAttachments;
import ru.sportmaster.tests.TestBase;

import static ru.sportmaster.helpers.DriverUtils.getSessionId;

@ExtendWith({AllureJunit5.class})
public class TestBaseWeb extends TestBase {
    @BeforeAll
    public static void setup() {
        BrowserDriver.configure();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = "";
        if (Project.isRemoteWebDriver()) {
            sessionId = getSessionId();
        }
        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        if (Project.isVideoOn()) {
            AllureAttachments.addVideoBrowser(sessionId);
        }
    }
}
