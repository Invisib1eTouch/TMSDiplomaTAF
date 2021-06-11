package apiSteps;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserApiSteps extends ApiSteps {

    static {
       RestAssured.basePath = "user.api";
    }

    public static Response getMe(){
        Response response = given()
                .header(new Header("Authorization", "Bearer " + authToken))
                .log().all()
                .get("/me");

        if (response.getStatusCode() != 200){
            response.prettyPrint();
        }

        return response;
    }
}
