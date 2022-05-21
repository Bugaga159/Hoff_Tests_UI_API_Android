package ru.hoff.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.hoff.config.Credentials;
import ru.hoff.drivers.BrowserstackMobileDriver;
import ru.hoff.drivers.LocalMobileDriver;
import ru.hoff.helpers.AllureAttachments;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static ru.hoff.helpers.DriverUtils.getSessionId;

public class TestBase {
    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        switch (System.getProperty("device")) {
            case "real":
            case "emulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            default:
                throw new IllegalArgumentException("Something strange happend");
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = "";
        if (Credentials.isBrowserStack()) {
            sessionId = getSessionId();
        }

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();

        closeWebDriver();

        if (Credentials.isBrowserStack()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
