package apiSteps;

import com.google.gson.reflect.TypeToken;
import dataObjects.json.cart.PositionToDeleteJson;
import dataObjects.json.cart.PositionsToDeleteJson;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CartApiSteps extends ApiSteps {

    protected CartApiSteps(String authToken) {
        super("cart.api", authToken);
    }

    public Response getCartPositions() {
        Response response = given(this.spec)
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .log().all()
                .get("positions");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }

    public Response deleteCartPositions(PositionsToDeleteJson positions) {
        Response response = given(this.spec)
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .contentType(ContentType.JSON)
                .body(gson.toJson(positions))
                .log().all()
                .delete("positions");

        if (response.getStatusCode() != 204) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }

    public void deleteAllCartPositionsIfExist() {
        List<PositionToDeleteJson> positionsToDelete =
                // TypeToken - specifying type that should be returned after deserialization from obtained json string
                gson.fromJson(this.getCartPositions().getBody().asString(), new TypeToken<ArrayList<PositionToDeleteJson>>() {
                }.getType());

        if (positionsToDelete.size() > 0)
            this.deleteCartPositions(new PositionsToDeleteJson(positionsToDelete));
    }
}
