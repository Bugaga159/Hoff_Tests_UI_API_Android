package ru.sportmaster.tests;

import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    public static void addAllureSelenide() {
        addListener("AllureSelenide", new AllureSelenide());
    }
}
