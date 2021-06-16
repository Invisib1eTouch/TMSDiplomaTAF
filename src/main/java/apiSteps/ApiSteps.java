package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import io.restassured.RestAssured;

public class ApiSteps {
    protected static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    protected static String authToken;

   static {
        RestAssured.baseURI = String.format("https://%s.%s/%s", PropertyReader.getUrlPrefix("default.url.prefix"),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
    }
}
