package ru.sportmaster.pages.web;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import ru.sportmaster.helpers.QueriesRestAssured;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainPage {

    @Step("open site")
    public MainPage openMain() {
        open("/");
        return this;
    }

    @Step("add goods to basket and cookies")
    public MainPage addGoodsAndCookies() {
        Response response = QueriesRestAssured.addGoodsToBasket();
        Map<String,String> cookies = response.cookies();
        getWebDriver().manage().deleteAllCookies();
        cookies.forEach((k,y) -> getWebDriver().manage().addCookie(new Cookie(k,y)));
        refresh();
        return this;
    }

    @Step("open basket site")
    public BasketPage goToBasket() {
        open("/cart/");
        return new BasketPage();
    }
}
