package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RequiredArgsConstructor
public class RestAssuredRequestFilter implements Filter {

    private final Logger log;
    private final int statusCode;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        var requestAndResponseInfo =
                String.format("%s %s" +
                                "\nStatus: %s" +
                                "\nHeaders: %s" +
                                "\nRequest Body: %s" +
                                "\nResponse Body: %s",
                        requestSpec.getMethod(),
                        requestSpec.getURI(),
                        response.getStatusLine(),
                        requestSpec.getHeaders().asList(),
                        requestSpec.getBody(),
                        response.getBody().asString());

        if (response.getStatusCode() != this.statusCode)
            log.error(requestAndResponseInfo);
        else
            log.info(requestAndResponseInfo);

        return response;
    }
}