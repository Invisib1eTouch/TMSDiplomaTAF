package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import enums.UrlPrefix;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    protected RequestSpecification spec;
    protected static final Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
    protected static String authToken;

    public synchronized static ApiSteps get() {
        RestAssured.baseURI = String.format("https://%s.%s/%s", UrlPrefix.DEFAULT.getValue(),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
        return new ApiSteps("");
    }

    protected ApiSteps(String basePath) {
        this.spec = given().basePath(basePath);
    }

    public ApiSteps login(String login, String password) {
        synchronized (ApiSteps.class){
            this.userApiSteps().postLogin(login, password);
            return this;
        }
    }

    public UserApiSteps userApiSteps() {
        synchronized (ApiSteps.class) {
            return new UserApiSteps();
        }
    }

    public CartApiSteps cartApiSteps() {
        synchronized (ApiSteps.class) {
            return new CartApiSteps();
        }
    }

    public CatalogApiSteps catalogApiSteps() {
        synchronized (ApiSteps.class) {
            return new CatalogApiSteps();
        }
    }
}
