package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import io.restassured.RestAssured;

public class ApiSteps {
    protected final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public ApiSteps(String path) {
        RestAssured.baseURI = String.format("https://%s.%s/%s", PropertyReader.getUrlPrefix("base.api.prefix"),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
        RestAssured.basePath = path;
    }
}
