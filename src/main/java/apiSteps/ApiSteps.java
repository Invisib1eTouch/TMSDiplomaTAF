package apiSteps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.PropertyReader;
import dataObjects.json.CredsJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    protected static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    protected static String authToken;

   static {
        RestAssured.baseURI = String.format("https://%s.%s/%s", PropertyReader.getUrlPrefix("base.api.prefix"),
                PropertyReader.getBaseUrl(), PropertyReader.getApiPath());
    }

    public static Response login(String login, String password) {
        Response response = given()
                .basePath("user.api")
                .contentType(ContentType.JSON)
                .body(gson.toJson(new CredsJson(login, password)))
                .log().all()
                .post("/login");

        if (response.getStatusCode() != 201){
            response.prettyPrint();
        }

        authToken = response.getBody().jsonPath().getString("access_token");
        return response;
    }
}
