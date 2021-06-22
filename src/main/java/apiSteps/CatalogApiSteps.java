package apiSteps;

import dataObjects.json.products.ProductsJson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import utils.RestAssuredRequestFilter;

import static io.restassured.RestAssured.given;

@Slf4j
public class CatalogApiSteps extends ApiSteps {

    protected CatalogApiSteps() {
        super("catalog.api", null);
    }

    @Step("[API] Get available mobile phones")
    public ProductsJson getAvailableMobilePhones() {
        Response response = given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .get("search/mobile?in_stock=1");

        return gson.fromJson(response.getBody().asString(), ProductsJson.class);
    }

    @Step("[API] Get search result by product name")
    public Response getSearchResultByProductName(String productName) {
        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .queryParam("query", productName)
                .get("search/products");
    }
}