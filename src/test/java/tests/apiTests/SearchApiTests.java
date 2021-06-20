package tests.apiTests;

import apiSteps.CatalogApiSteps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.AllureUtilities;
import utils.Utils;

public class SearchApiTests {
    @AfterClass
    public void tearDown() throws Exception {
        AllureUtilities.removeParametersInReport();
    }

    @Test
    public void searchResultsTest() {
        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        String extendedProductName = product.getExtendedName();

        var searchResultsResponse = CatalogApiSteps.getSearchResultByProductName(extendedProductName);
        Assert.assertTrue(searchResultsResponse
                .getBody()
                .jsonPath()
                .getList("products.extended_name")
                .contains(extendedProductName));
    }
}
