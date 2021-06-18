package tests;

import apiSteps.CartApiSteps;
import apiSteps.CatalogApiSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterClassDriverDisposing;
import utils.Utils;

import static com.codeborne.selenide.Condition.*;

public class PopoverTests extends BaseTestAfterClassDriverDisposing {
    private String productFullName;

    @BeforeClass
    public void testSetup() {
        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.productFullName = product.getFullName();
    }

    @Test
    public void locationPopoverTest() {
        new MainPageSteps(true)
                .searchProduct(productFullName)
                .openProductDetailsPageByName(productFullName)
                .openProductOffersPageThroughPrice()
                .getPageInstance()
                .getLocationPopoverOnFirstPageVisit()
                .getPopoverContainer()
                .should(exist, have(text("Ваш населенный пункт")));

        CartApiSteps.deleteAllCartPositionsIfExist();

    }
}
