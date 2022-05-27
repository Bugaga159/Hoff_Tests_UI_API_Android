package ru.sportmaster.tests.mobile;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.sportmaster.config.Credentials;
import ru.sportmaster.helpers.Layer;
import ru.sportmaster.tests.TestBase;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Layer("mobile")
public class SmokeTest extends TestBase {

    @Test
    @Tag("Android")
    @Owner("Panin")
    @Story("Добавить товар в корзину")
    @Description("Проверка на добавление товара в корзину и количество товара в корзине равное 1")
    @DisplayName("Добавить товар в пустую корзину через поиск")
    void addGoodsToCartTest() {
        step("Открытие главной страницы и настройка города", () -> {
            $(AppiumBy.className("android.widget.ImageButton")).click();
            $(AppiumBy.className("android.widget.ImageButton")).click();

            if (Credentials.isBrowserStack()) {
                $(AppiumBy.id("com.android.packageinstaller:id/permission_deny_button")).click();
            } else {
                $(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button")).click();
            }

            $(AppiumBy.id("ru.sportmaster.app:id/textViewCity")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/textViewCity")).click();
        });
        step("Ввод текста 'Коньки' в поле поиска", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).sendKeys("Коньки");
            $(AppiumBy.id("ru.sportmaster.app:id/viewHintClickArea"))
                    .should(visible, Duration.ofSeconds(10)).click();
        });
        step("Добавить первый товар в списке в корзину", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/buttonCart")).click();
        });
        step("Выборать размер", () -> {
            $(AppiumBy.className("androidx.cardview.widget.CardView")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/buttonFinishSelect")).click();
        });
        step("Перейти в корзину", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/ordering_graph")).click();
        });
        step("Проверки в корзине", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/frameLayoutFavorite")).click();
            $$(AppiumBy.id("ru.sportmaster.app:id/swipeLayout")).shouldHave(CollectionCondition.size(1));
            $(AppiumBy.id("ru.sportmaster.app:id/textViewName")).shouldHave(Condition.text("Коньки"));
            $(AppiumBy.id("ru.sportmaster.app:id/textViewCount")).shouldHave(Condition.text("1"));
        });
    }

    @Test
    @Tag("Android")
    @Owner("Panin")
    @Story("Добавить товар в сравнение")
    @Description("Проверка на добавление товара в сравнение и количество товара равное 2")
    @DisplayName("Добавить товары в сравнение через поиск")
    void addToComparisonTest() {
        step("Открытие главной страницы и настройка города", () -> {
            $(AppiumBy.className("android.widget.ImageButton")).click();
            $(AppiumBy.className("android.widget.ImageButton")).click();

            if (Credentials.isBrowserStack()) {
                $(AppiumBy.id("com.android.packageinstaller:id/permission_deny_button")).click();
            } else {
                $(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button")).click();
            }

            $(AppiumBy.id("ru.sportmaster.app:id/textViewCity")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/textViewCity")).click();
        });
        step("Ввод текста 'Коньки' в поле поиска", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).click();
            $(AppiumBy.id("ru.sportmaster.app:id/editText")).sendKeys("Коньки");
            $(AppiumBy.id("ru.sportmaster.app:id/viewHintClickArea"))
                    .should(visible, Duration.ofSeconds(10)).click();
        });
        step("Добавить два товара в сравнение", () -> {
            $$(AppiumBy.id("ru.sportmaster.app:id/fabCompare")).get(0).click();
            $$(AppiumBy.id("ru.sportmaster.app:id/fabCompare")).get(1).click();
        });
        step("Перейти на страницу сравнение", () -> {
            $(AppiumBy.id("ru.sportmaster.app:id/frameLayoutCompare")).click();
        });
        step("Проверки в корзине", () -> {
            $(AppiumBy.className("android.widget.TextView")).shouldHave(Condition.text("Список сравнения (2)"));
        });
    }
}
