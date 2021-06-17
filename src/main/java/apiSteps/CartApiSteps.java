package apiSteps;

import com.google.gson.reflect.TypeToken;
import dataObjects.json.cart.PositionToDeleteJson;
import dataObjects.json.cart.PositionsToDeleteJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CartApiSteps extends ApiSteps {

    static {
        RestAssured.basePath = "cart.api";
    }

    public static Response getCartPositions() {
        Response response = given()
                .header(new Header("Authorization", "Bearer " + authToken))
                .log().all()
                .get("positions");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }

        return response;
    }

    public static Response deleteCartPositions(PositionsToDeleteJson positions) {
        Response response = given()
                .header(new Header("Authorization", "Bearer " + authToken))
                .contentType(ContentType.JSON)
                .body(gson.toJson(positions))
                .log().all()
                .delete("positions");

        if (response.getStatusCode() != 204) {
            response.prettyPrint();
        }

        return response;
    }

    public static void deleteAllCartPositionsIfExist() {
        List<PositionToDeleteJson> positionsToDelete =
                // TypeToken - specifying type that should be returned after deserialization from obtained json string
                gson.fromJson(getCartPositions().getBody().asString(), new TypeToken<ArrayList<PositionToDeleteJson>>() {
                }.getType());

        if (positionsToDelete.size() > 0)
            deleteCartPositions(new PositionsToDeleteJson(positionsToDelete));
    }
}
