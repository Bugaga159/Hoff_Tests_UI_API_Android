package ru.sportmaster.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.sportmaster.config.Credentials;
import ru.sportmaster.drivers.BrowserDriver;
import ru.sportmaster.drivers.BrowserstackMobileDriver;
import ru.sportmaster.drivers.LocalMobileDriver;
import ru.sportmaster.helpers.AllureAttachments;
import ru.sportmaster.helpers.DriverUtils;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static ru.sportmaster.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static ru.sportmaster.helpers.DriverUtils.getSessionId;

@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        if (!Credentials.isApiTest()) {
            switch (Credentials.config.client()) {
                case "real":
                case "emulator":
                    Configuration.browser = LocalMobileDriver.class.getName();
                    Configuration.browserSize = null;
                    break;
                case "browserstack":
                    Configuration.browser = BrowserstackMobileDriver.class.getName();
                    Configuration.browserSize = null;
                    break;
                case "remoteBrowser":
                case "localBrowser":
                    BrowserDriver.configure();
                    break;
                default:
                    throw new IllegalArgumentException("Something strange happened");
            }
        } else {
            RestAssured.baseURI = Credentials.config.baseUrl();
            RestAssured.filters(withCustomTemplates());
        }
    }

    @BeforeEach
    public void startDriver() {
        if (Credentials.isMobile()) {
            open();
        }
    }

    @AfterEach
    public void afterEach() {
        if (!Credentials.isApiTest()) {
            String sessionId = "";
            if (Credentials.isBrowserStack() || Credentials.isRemoteWebDriver()) {
                sessionId = getSessionId();
            }

            AllureAttachments.addScreenshotAs("Last screenshot");
            AllureAttachments.addPageSource();
            AllureAttachments.addBrowserConsoleLogs();

            closeWebDriver();

            if (Credentials.isBrowserStack()) {
                AllureAttachments.addVideoBrowserstack(sessionId);
            }
            if (Credentials.isVideoOn()) {
                AllureAttachments.addVideoBrowser(sessionId);
            }
        }
    }
}
