package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import enums.UrlPrefix;
import io.restassured.RestAssured;

public class ApiSteps {
    protected UserApiSteps userApiSteps;
    protected CartApiSteps cartApiSteps;
    protected CatalogApiSteps catalogApiSteps;

    protected static final Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
    protected String authToken;

    public static ApiSteps get() {
        RestAssured.baseURI = String.format("https://%s.%s/%s", UrlPrefix.DEFAULT.getValue(),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
        return new ApiSteps();
    }

    protected ApiSteps() {}

    public ApiSteps login(String login, String password) {
        this.userApiSteps().postLogin(login, password);
        return this;
    }

    public UserApiSteps userApiSteps() {
        if(this.userApiSteps == null)
            this.userApiSteps = new UserApiSteps();
        return this.userApiSteps;
    }

    public CartApiSteps cartApiSteps() {
        if(this.cartApiSteps == null)
            this.cartApiSteps = new CartApiSteps();
        return this.cartApiSteps;
    }

    public CatalogApiSteps catalogApiSteps() {
        if(this.catalogApiSteps == null)
            this.catalogApiSteps = new CatalogApiSteps();
        return this.catalogApiSteps;
    }
}
