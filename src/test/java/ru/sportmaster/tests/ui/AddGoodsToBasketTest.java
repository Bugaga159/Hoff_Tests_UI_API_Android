package ru.sportmaster.tests.ui;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.sportmaster.tests.TestBase;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AddGoodsToBasketTest extends TestBase {

    @Test
    @Tag("UI")
    @Description("Проверка на добавление товара в корзину и количество товара в корзине равное 1")
    @DisplayName("Добавить товар в пустую корзину через карточку товара")
    void addGoodsToEmptyBasketFromDetailPage() {
        step("Open 'https://www.sportmaster.ru'", () -> {
            open("/product/23117750299/");
        });
        step("Выбрать размер", () -> {
            $("[data-selenium='product-sizes-item']").click();
            $(byText("В корзину")).click();
        });
        step("Кликнуть на 'Перейти в корзину'", () -> {
            $(byText("Перейти в корзину")).click();
        });
        step("Проверки на странице фильма", () -> {
            $$("[data-selenium='basket-item']").shouldHave(CollectionCondition.size(1));
        });
    }

    @Test
    @Tag("UI")
    @Description("Проверка на добавление товара в корзину и количество товара в корзине равное 1")
    @DisplayName("Добавить товар в пустую корзину через поиск")
    void addGoodsToEmptyBasketFromSearchPage() {
        step("Open 'https://www.sportmaster.ru'", () -> {
            open("/");
        });
        step("Ввод текста 'Коньки' в поле поиска и нажать Enter", () -> {
            $("[data-selenium='smTextField']").setValue("Коньки").pressEnter();
        });
        step("Кликнуть на первый товара", () -> {
            $("[data-selenium='product-name']").scrollTo().hover();
            $("[data-selenium='addToCartButton']").click();
        });
        step("Выбрать размер", () -> {
            $("[data-selenium='product-sizes-item']").click();
            $("button[data-selenium='add-btn']").click();
        });

        step("Кликнуть на 'Перейти в корзину'", () -> {
            $("[data-selenium='sm_badge_item']").click();
        });
        step("Проверки на странице фильма", () -> {
            $$("[data-selenium='basket-item']").shouldHave(CollectionCondition.size(1));
        });
    }
}
