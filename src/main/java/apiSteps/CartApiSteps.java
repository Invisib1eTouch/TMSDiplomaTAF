package apiSteps;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import dataObjects.json.cart.PositionToDeleteJson;
import dataObjects.json.cart.PositionsToDeleteJson;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import utils.RestAssuredRequestFilter;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class CartApiSteps extends ApiSteps {

    protected CartApiSteps(String authToken) {
        super("cart.api", authToken);
    }

    @Step("[API] Get cart positions")
    public Response getCartPositions() {
        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .get("positions");
    }

    @Step("[API] Delete cart positions")
    public Response deleteCartPositions(PositionsToDeleteJson positions) {
        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 204))
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .contentType(ContentType.JSON)
                .body(gson.toJson(positions))
                .delete("positions");
    }

    @SneakyThrows
    @Step("[API] Delete existing cart positions")
    public void deleteAllCartPositionsIfExist() {
        List<PositionToDeleteJson> positionsToDelete = null;
        String stringResponse = null;
        try{
            stringResponse = this.getCartPositions().getBody().asString();
            // TypeToken - specifying type that should be returned after deserialization from obtained json string
            positionsToDelete = gson.fromJson(stringResponse, new TypeToken<ArrayList<PositionToDeleteJson>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("Response body: " + stringResponse + "Message: " + e.getMessage());
            throw new JsonSyntaxException(e.getMessage());
        }

        if (positionsToDelete.size() > 0)
            this.deleteCartPositions(new PositionsToDeleteJson(positionsToDelete));
    }
}