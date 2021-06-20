package apiSteps;

import dataObjects.json.CredsJson;
import dataObjects.json.user.UserDataJson;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiSteps extends ApiSteps {

    protected UserApiSteps(String authToken) {
        super("user.api", authToken);
    }

    public Response postLogin(String login, String password) {
        Response response = given(this.spec)
                .contentType(ContentType.JSON)
                .body(gson.toJson(new CredsJson(login, password)))
                .log().all()
                .post("login");

        if (response.getStatusCode() != 201) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }

    public Response getMe() {
        Response response = given(this.spec)
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .log().all()
                .get("me");

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }

    public Response getInternalUserInfoById(String userId) {

        Response response = given(this.spec)
                .log().all()
                .get("users/{userId}", userId);

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }

    public Response removeProfileHeaderCoverImage() {
        var userId = this.getMe().getBody().jsonPath().getInt("id");

        Response response = given(this.spec)
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .contentType(ContentType.JSON)
                .body(gson.toJson(new UserDataJson().setCover(null)))
                .log().all()
                .patch("users/{userId}", userId);

        if (response.getStatusCode() != 200) {
            response.prettyPrint();
        }
        response.prettyPrint();

        return response;
    }
}
