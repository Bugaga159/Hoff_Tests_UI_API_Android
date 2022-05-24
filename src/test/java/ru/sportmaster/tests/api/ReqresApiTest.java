package ru.sportmaster.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.sportmaster.helpers.QueriesRestAssured;
import ru.sportmaster.models.addToBasket.request.ItemsGoods;
import ru.sportmaster.models.addToBasket.request.ReqAddGoodsToBasket;
import ru.sportmaster.models.addToBasket.response.RespAddToBasket;
import ru.sportmaster.models.addToComparison.request.ReqProductId;
import ru.sportmaster.models.addToComparison.response.RespProductIds;
import ru.sportmaster.models.getProduct.response.RespProduct;
import ru.sportmaster.tests.TestBase;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresApiTest extends TestBase {

    @Test
    @Tag("API")
    @Description("Получить данные о товаре")
    @DisplayName("GET /product-view/{id} - 200")
    public void getProductById200Test(){
        String prodId = "26149430299";
        RespProduct res = QueriesRestAssured.getProductById(prodId, 200).as(RespProduct.class);
        assertGetProductById(prodId, res);
    }

    @Test
    @Tag("API")
    @Description("Получить данные о товаре")
    @DisplayName("GET /product-view/{id} - 400")
    public void getProductById400Test(){
        String prodId = "2614943ddd0299";
        QueriesRestAssured.getProductById(prodId, 400);
    }

    @Test
    @Tag("API")
    @Description("Получить данные о товаре")
    @DisplayName("GET /product-view/{id} - 404")
    public void getProductById404Test(){
        String prodId = "2617474874387878379";
        QueriesRestAssured.getProductById(prodId, 404);
    }

    @Test
    @Tag("API")
    @Description("Добавить товар в пустую корзину")
    @DisplayName("POST /cart/products/quantity/ - 200")
    public void addToBasket200Test(){
        ItemsGoods goods = ItemsGoods.builder()
                .productId("23117750299")
                .quantity(1)
                .wareId("178160260299")
                .build();

        ReqAddGoodsToBasket basket = createTestObject(goods);
        RespAddToBasket res = QueriesRestAssured.postProductToCart(basket, 200).as(RespAddToBasket.class);
        assertPostResProductToCart(goods, res);
    }

    @ParameterizedTest
    @CsvSource({
            "dfff,213",
            "1246,sdet",
            "23117d750299,178160260r299"})
    @Tag("API")
    @Description("Добавить товар в пустую корзину с invalid id")
    @DisplayName("POST /cart/products/quantity/ - 400")
    public void addToBasket400Test(String productId, String wareId){
        ItemsGoods goods = ItemsGoods.builder()
                .productId(productId)
                .quantity(1)
                .wareId(wareId)
                .build();

        ReqAddGoodsToBasket basket = createTestObject(goods);
        QueriesRestAssured.postProductToCart(basket, 400);
    }

    @Test
    @Tag("API")
    @Description("Добавить товар в сравнение")
    @DisplayName("PUT /comparison/products/ - 200")
    public void addToComparison200Test(){
        ReqProductId product = ReqProductId.builder().productId("25549950299").build();
        RespProductIds res = QueriesRestAssured.putGoodsToComparison(product, 200).as(RespProductIds.class);
        assertPutGoodsToComparison(product, res);
    }

    @Test
    @Tag("API")
    @Description("Добавить товар в сравнение c invalid id")
    @DisplayName("PUT /comparison/products/ - 400")
    public void addToComparison400Test(){
        ReqProductId product = ReqProductId.builder().productId("255499cccc50299").build();
        RespProductIds res = QueriesRestAssured.putGoodsToComparison(product, 400).as(RespProductIds.class);
        assertPutGoodsToComparison(product, res);
    }

    @Test
    @Tag("API")
    @Description("Добавить товар в сравнение c empty id")
    @DisplayName("PUT /comparison/products/ - 400")
    public void addToComparison400WithoutIdTest(){
        ReqProductId product = ReqProductId.builder().productId("").build();
        RespProductIds res = QueriesRestAssured.putGoodsToComparison(product, 400).as(RespProductIds.class);
        assertPutGoodsToComparison(product, res);
    }

    @Test
    @Tag("API")
    @Description("Добавить товар в сравнение c несуществующим id")
    @DisplayName("PUT /comparison/products/ - 404")
    public void addToComparison404Test(){
        ReqProductId product = ReqProductId.builder().productId("25549757433565").build();
        RespProductIds res = QueriesRestAssured.putGoodsToComparison(product, 404).as(RespProductIds.class);
        assertPutGoodsToComparison(product, res);
    }

    @Step("проверки response api /product-view/{id}")
    private void assertGetProductById(String prodId, RespProduct res) {
        step("assert ProductId = " + prodId, () -> assertThat(
                res.getProductId())
                .isEqualTo(prodId));
        step("assert ProductName = 'Кеды мужские Vans Doheny'", () -> assertThat(
                res.getProductName())
                .isEqualTo("Кеды мужские Vans Doheny"));
        step("assert ProductGroup = 'Кеды'", () -> assertThat(
                res.getProductGroup())
                .isEqualTo("Кеды"));
        step("assert ProductType = 'Обувь'", () -> assertThat(
                res.getProductType())
                .isEqualTo("Обувь"));
    }

    @Step("проверки response api '/comparison/products/'")
    private void assertPutGoodsToComparison(ReqProductId product, RespProductIds res) {
        step("assert size", () -> assertThat(
                res.getProductIds().size())
                .isEqualTo(1));
        step("assert ProductId in the list", () -> assertThat(
                res.getProductIds().get(0))
                .isEqualTo(product.getProductId()));
    }

    @Step("проверки response api '/cart/products/quantity/'")
    private void assertPostResProductToCart(ItemsGoods goods, RespAddToBasket res) {
        step("assert size", () -> assertThat(
                res.getData().getItems().size())
                .isEqualTo(1));
        step("assert Quantity", () -> assertThat(
                res.getData().getTotal().getProductsQuantity())
                .isEqualTo(1));
        step("assert ProductId", () -> assertThat(
                res.getData().getItems().get(0).getProduct().getProductId())
                .isEqualTo(goods.getProductId()));
        step("assert WareId", () -> assertThat(
                res.getData().getItems().get(0).getProduct().getWareId())
                .isEqualTo(goods.getWareId()));
    }

    @Step("создать объекта для запроса на добовление в корзину")
    private ReqAddGoodsToBasket createTestObject(ItemsGoods goods) {
        List<ItemsGoods> items = new ArrayList<>();
        items.add(goods);
        return ReqAddGoodsToBasket.builder()
                .cartFormat("SIMPLE")
                .items(items)
                .build();
    }
}
