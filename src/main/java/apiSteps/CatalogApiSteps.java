package apiSteps;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CatalogApiSteps extends ApiSteps {

    static {
        RestAssured.basePath = "catalog.api/search";
    }

    public static Response getAvailableMobilePhones() {
        Response response = given()
                .log().all()
                .get("mobile?in_stock=1");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }

        return response;
    }
}