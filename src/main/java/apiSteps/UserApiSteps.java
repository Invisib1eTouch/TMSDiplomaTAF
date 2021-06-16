package apiSteps;

import dataObjects.json.CredsJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiSteps extends ApiSteps {

    static {
       RestAssured.basePath = "user.api";
    }

    public static Response login(String login, String password) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(new CredsJson(login, password)))
                .log().all()
                .post("login");

        if (response.getStatusCode() != 201){
            response.prettyPrint();
        }

        authToken = response.getBody().jsonPath().getString("access_token");
        return response;
    }

    public static Response getMe(){
        Response response = given()
                .header(new Header("Authorization", "Bearer " + authToken))
                .log().all()
                .get("me");

        if (response.getStatusCode() != 200){
            response.prettyPrint();
        }

        return response;
    }
}
