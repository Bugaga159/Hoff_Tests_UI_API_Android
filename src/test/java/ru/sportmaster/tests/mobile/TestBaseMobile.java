package ru.sportmaster.tests.mobile;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.sportmaster.config.Project;
import ru.sportmaster.drivers.BrowserstackMobileDriver;
import ru.sportmaster.drivers.LocalMobileDriver;
import ru.sportmaster.helpers.AllureAttachments;
import ru.sportmaster.tests.TestBase;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static ru.sportmaster.helpers.DriverUtils.getSessionId;

@ExtendWith({AllureJunit5.class})
public class TestBaseMobile extends TestBase {
    @BeforeAll
    public static void setup() {
        switch (Project.config.client()) {
            case "real":
            case "emulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            default:
                throw new IllegalArgumentException("Something strange happened");
        }
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = "";
        if (Project.isBrowserStack()) {
            sessionId = getSessionId();
        }
        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();
        closeWebDriver();
        if (Project.isBrowserStack()) {
            AllureAttachments.addVideoBrowserstack(sessionId);
        }
    }
}
