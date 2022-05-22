package ru.sportmaster.pages.web;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {
    private SelenideElement deleteAll = $("[data-selenium='cart-group-delete']");
    private ElementsCollection itemGoods = $$("[data-selenium='basket-item']");

    @Step("delete goods all")
    public BasketPage deleteGoods() {
        deleteAll.click();
        return this;
    }

    @Step("delete goods all")
    public BasketPage checkItems(int count) {
        itemGoods.shouldHave(size(count));
        return this;
    }
}
