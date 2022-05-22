package ru.sportmaster.helpers;

import com.google.common.collect.Lists;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.sportmaster.models.addtoBasket.request.ItemsGoods;
import ru.sportmaster.models.addtoBasket.request.ReqAddGoodsToBasket;
import java.util.List;

import static io.restassured.RestAssured.given;

public class QueriesRestAssured {
    public static Response addGoodsToBasket(){
        List<ItemsGoods> items = Lists.newArrayList(ItemsGoods.builder()
                .productId("23117750299")
                .quantity(1)
                .wareId("178160260299")
                .build());
        ReqAddGoodsToBasket basket = ReqAddGoodsToBasket.builder()
                .cartFormat("SIMPLE")
                .items(items)
                .build();

        return given()
                .basePath("/web-api/v1")
                .contentType(ContentType.JSON)
                .header("x-sm-accept-language", "ru-RU")
                .body(basket)
                .log().all()
                .post("/cart/products/quantity/")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
    }
}
