package tests;

import apiSteps.ApiSteps;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.productPagesSteps.ProductDetailsPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import utils.Utils;

import static com.codeborne.selenide.Condition.*;

public class PopupTests extends BaseTestAfterMethodDriverDisposing {

    private String productUrl;

    @BeforeMethod
    public void testSetup() {
        var products = ApiSteps.get()
                .catalogApiSteps()
                .getAvailableMobilePhones()
                .getProducts();

        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.productUrl = product.getHtmlUrl();
    }

    @Test(description = "Location popup test")
    @Description("Display of Location popup.")
    public void locationPopoverTest() {
        final String popoverTitle = "Ваш населенный пункт";

        ProductDetailsPageSteps
                .openPage(this.productUrl)
                .openProductOffersPageThroughPrice()
                .getPageInstance()
                .getLocationPopoverOnFirstPageVisit()
                .getPopupContainer()
                .should(exist, have(text(popoverTitle)));
    }
}