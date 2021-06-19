package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import enums.UrlPrefix;
import io.restassured.RestAssured;

public class ApiSteps {

    protected static final Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
    protected String authToken;

    public static ApiSteps get() {
        if(RestAssured.baseURI == null) {
            RestAssured.baseURI = String.format("https://%s.%s/%s", UrlPrefix.DEFAULT.getValue(),
                    PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
        }
        return new ApiSteps();
    }

    protected ApiSteps() {}

    public ApiSteps login(String login, String password){
        this.userApiSteps().postLogin(login, password);
        return this;
    }

    public UserApiSteps userApiSteps() {
        return new UserApiSteps();
    }

    public CartApiSteps cartApiSteps() {
        return new CartApiSteps();
    }

    public CatalogApiSteps catalogApiSteps() {
        return new CatalogApiSteps();
    }
}
