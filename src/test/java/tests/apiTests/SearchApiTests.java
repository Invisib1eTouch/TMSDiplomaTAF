package tests.apiTests;

import apiSteps.ApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

public class SearchApiTests {

//    @Test(description = "Search results test")
//    @Description("Searching for product by name.")
//    public void searchResultsTest() {
//        var products = ApiSteps.get()
//                .catalogApiSteps()
//                .getAvailableMobilePhones()
//                .getProducts();
//
//        var product = products.get(Utils.getRandomNumber(0, products.size()));
//        String extendedProductName = product.getExtendedName();
//
//        var searchResultsResponse = ApiSteps.get()
//                .catalogApiSteps()
//                .getSearchResultByProductName(extendedProductName);
//
//        Assert.assertTrue(searchResultsResponse
//                .getBody()
//                .jsonPath()
//                .getList("products.extended_name")
//                .contains(extendedProductName));
//    }
}