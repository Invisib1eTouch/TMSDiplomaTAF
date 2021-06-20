package apiSteps;

import dataObjects.json.products.ProductsJson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CatalogApiSteps extends ApiSteps {

    protected CatalogApiSteps() {
        RestAssured.basePath = "catalog.api/search";
    }

    public ProductsJson getAvailableMobilePhones() {
        Response response = given()
                .log().all()
                .get("mobile?in_stock=1");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return gson.fromJson(response.getBody().asString(), ProductsJson.class);
    }

    public Response getSearchResultByProductName(String productName) {
        Response response = given()
                .queryParam("query", productName)
                .log().all()
                .get("products");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }
}