package apiSteps;

import dataObjects.json.products.ProductsJson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CatalogApiSteps extends ApiSteps {

    protected CatalogApiSteps() {
        super("catalog.api", null);
    }

    public ProductsJson getAvailableMobilePhones() {
        Response response = this.spec
                .log().all()
                .get("search/mobile?in_stock=1");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return gson.fromJson(response.getBody().asString(), ProductsJson.class);
    }

    public Response getSearchResultByProductName(String productName) {
        Response response = this.spec
                .queryParam("query", productName)
                .log().all()
                .get("search/products");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }
}