package tests.apiTests;

import apiSteps.CatalogApiSteps;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

public class SearchApiTests {

    @Test
    public void searchResultsTest() {
        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        String extendedProductName = product.getExtendedName();

        var searchResultsResponse = CatalogApiSteps.getSearchResultNameByName(extendedProductName);
        Assert.assertTrue(searchResultsResponse
                .getBody()
                .jsonPath()
                .getList("products.extended_name")
                .contains(extendedProductName));
    }
}
