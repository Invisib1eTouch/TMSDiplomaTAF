package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import enums.UrlPrefix;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ApiSteps {

    protected RequestSpecification spec;
    protected static final Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
    protected String authToken;

    public synchronized static ApiSteps get() {
        RestAssured.baseURI = String.format("https://%s.%s/%s", UrlPrefix.DEFAULT.getValue(),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
        return new ApiSteps("", null);
    }

    protected ApiSteps(String basePath, String authToken) {
        this.spec = given().basePath(basePath);
        this.authToken = authToken;
    }

    @Step("Login with credentials: ({login}) / ({password}).")
    public ApiSteps login(String login, String password) {
        synchronized (ApiSteps.class) {
            this.authToken = this.userApiSteps()
                    .postLogin(login, password)
                    .getBody()
                    .jsonPath()
                    .getString("access_token");
            return this;
        }
    }

    public UserApiSteps userApiSteps() {
        synchronized (ApiSteps.class) {
            return new UserApiSteps(this.authToken);
        }
    }

    public CartApiSteps cartApiSteps() {
        synchronized (ApiSteps.class) {
            return new CartApiSteps(this.authToken);
        }
    }

    public CatalogApiSteps catalogApiSteps() {
        synchronized (ApiSteps.class) {
            return new CatalogApiSteps();
        }
    }
}
