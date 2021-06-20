package tests;

import apiSteps.CatalogApiSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.SQLRequestSender;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import utils.Utils;

import static com.codeborne.selenide.Condition.*;

public class PopupTests extends BaseTestAfterMethodDriverDisposing {
    private String productFullName;

    @BeforeMethod
    public void testSetup() {
        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.productFullName = product.getFullName();
    }

    @AfterMethod
    public void testTearDown(){
        SQLRequestSender.deleteCartItemByProductName(productFullName);
    }

    @Test
    public void locationPopoverTest() {
        final String popoverTitle = "Ваш населенный пункт";

        new MainPageSteps(true)
                .searchProduct(productFullName)
                .openProductDetailsPageByName(productFullName)
                .openProductOffersPageThroughPrice()
                .getPageInstance()
                .getLocationPopoverOnFirstPageVisit()
                .getPopupContainer()
                .should(exist, have(text(popoverTitle)));
    }
}
