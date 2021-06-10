package apiSteps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiSteps extends ApiSteps{

    public UserApiSteps() {
        super("user.api");
    }

    public Response login(String login, String password){
        given()
                .contentType(ContentType.JSON)
                .body()
    }

}
