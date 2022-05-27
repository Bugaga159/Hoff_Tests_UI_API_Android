package ru.sportmaster.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.sportmaster.helpers.DriverUtils;
import ru.sportmaster.helpers.Layer;
import ru.sportmaster.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Layer("web")
public class CheckSiteTest extends TestBase {

    @Test
    @Tag("UI")
    @Owner("Panin")
    @Story("Smoke check page")
    @Description("Проверка title сайта sportmaster")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open 'https://www.sportmaster.ru/'", () ->
                open("/"));

        step("Page title should have text 'Спортмастер — спортивный магазин для всей семьи!'", () -> {
            String expectedTitle = "Спортмастер — спортивный магазин для всей семьи!";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("UI")
    @Owner("Panin")
    @Story("Smoke check page")
    @Description("Проверка на отсутствие ошибок в консоли сайта")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open 'https://www.sportmaster.ru/'", () ->
            open("/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
