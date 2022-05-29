package ru.sportmaster.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.sportmaster.helpers.Layer;
import ru.sportmaster.pages.web.MainPage;

@Layer("web")
public class DeleteGoodsFromBasketTest extends TestBaseWeb {
    MainPage mainPage = new MainPage();

    @Test
    @Tag("UI")
    @Owner("Panin")
    @Story("Удаление товара из корзины")
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
