package apiSteps;

import dataObjects.json.CredsJson;
import dataObjects.json.user.UserDataJson;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import utils.RestAssuredRequestFilter;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserApiSteps extends ApiSteps {

    protected UserApiSteps(String authToken) {
        super("user.api", authToken);
    }

    @Description("Login with credentials: ({login} / {password}).")
    public Response postLogin(String login, String password) {
        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .contentType(ContentType.JSON)
                .body(gson.toJson(new CredsJson(login, password)))
                .post("login");
    }

    @Step("Get personal information.")
    public Response getMe() {
        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .get("me");
    }

    @Step("Get internal personal information by user id: '{userId}'.")
    public Response getInternalUserInfoById(String userId) {
        return given(this.spec)
                .get("users/{userId}", userId);
    }

    @Step("Remove profile header image.")
    public Response removeProfileHeaderCoverImage() {
        var userId = this.getMe().getBody().jsonPath().getInt("id");

        return given(this.spec)
                .filter(new RestAssuredRequestFilter(log, 200))
                .header(new Header("Authorization", "Bearer " + this.authToken))
                .contentType(ContentType.JSON)
                .body(gson.toJson(new UserDataJson().setCover(null)))
                .patch("users/{userId}", userId);
    }
}
