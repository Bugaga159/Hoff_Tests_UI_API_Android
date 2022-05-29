package ru.sportmaster.helpers;

import com.google.common.collect.Lists;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.sportmaster.models.addToBasket.request.ItemsGoods;
import ru.sportmaster.models.addToBasket.request.ReqAddGoodsToBasket;
import ru.sportmaster.models.addToComparison.request.ReqProductId;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepsRestAssured {

    private static RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBasePath("/web-api/v1")
            .setContentType(ContentType.JSON)
            .addHeader("x-sm-accept-language", "ru-RU")
            .build();


    /**
     * POST запрос /cart/products/quantity/ на добавление товара в корзину
     * и используется для получения cookies в UI тестах
     * @return Response запроса
     * @param productId
     * @param wareId
     */
    public static Response addGoodsToBasket(String productId, String wareId){
        List<ItemsGoods> items = Lists.newArrayList(ItemsGoods.builder()
                .productId(productId)
                .quantity(1)
                .wareId(wareId)
                .build());
        ReqAddGoodsToBasket basket = ReqAddGoodsToBasket.builder()
                .cartFormat("SIMPLE")
                .items(items)
                .build();

        return given()
                .spec(reqSpec)
                .body(basket)
                .log().all()
                .post("/cart/products/quantity/")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
    }

    /**
     * POST /cart/products/quantity/ на добавление товара в корзину
     * @param basket - объект товара ReqAddGoodsToBasket для добавления в корзину
     * @return Response запроса
     */
    @Step("создание запроса на добавление товара в корзину")
    public static Response postProductToCart(ReqAddGoodsToBasket basket, int code) {
        return given()
                .spec(reqSpec)
                .body(basket)
                .log().all()
                .post("/cart/products/quantity/")
                .then()
                .log().all()
                .assertThat().statusCode(code)
                .extract().response();
    }

    /**
     * PUT /comparison/products/ на добавление товара в сравнение
     * @param prod - id товара для сравнения
     * @return Response запроса
     */
    @Step("создание запроса на добавление товара в сравнение")
    public static Response putGoodsToComparison(ReqProductId prod, int code) {
        return given()
                .spec(reqSpec)
                .body(prod)
                .log().all()
                .put("/comparison/products/")
                .then()
                .log().all()
                .assertThat().statusCode(code)
                .extract().response();
    }

    /**
     * GET /product-view/{id} получение информации о товаре
     * @param id - id товара
     * @param code - status code
     * @return Response запроса
     */
    @Step("Создание запроса на получение товара по Id")
    public static Response getProductById(String id, int code) {
        return given()
                .basePath("/ga-api/v1")
                .contentType(ContentType.JSON)
                .header("x-sm-accept-language", "ru-RU")
                .log().all()
                .get("/product-view/" + id)
                .then()
                .log().all()
                .assertThat().statusCode(code)
                .extract().response();
    }
}
