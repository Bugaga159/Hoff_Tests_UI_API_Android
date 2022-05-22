package ru.sportmaster.tests.ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.sportmaster.pages.web.MainPage;
import ru.sportmaster.tests.TestBase;

public class DeleteGoodsFromBasketTest extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    @Tag("UI")
    @Description("Удаление товара из корзины")
    @DisplayName("Удаление товара из корзины")
    void deleteAllGoodsFromBasket() {
        mainPage.openMain()
                .addGoodsAndCookies()
                .goToBasket()
                .checkItems(1)
                .deleteGoods()
                .checkItems(0);
    }
}
