package tests.apiTests;

import apiSteps.CartApiSteps;
import apiSteps.CatalogApiSteps;
import apiSteps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import utils.Utils;

public class SearchApiTests {

    private MainPageSteps mainPageSteps;
    private String extendedProductName;


    @Test
    public void searchResultsTest() {
//        SearchResultsFrameSteps searchResultsFrameSteps = this.mainPageSteps
//                .searchProduct(this.extendedProductName);

        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.extendedProductName = product.getExtendedName();

        var response = CatalogApiSteps.getSearchResultNameByName(this.extendedProductName);
        Assert.assertEquals(response.getBody().jsonPath().get("extended_name"), this.extendedProductName);
    }
}
