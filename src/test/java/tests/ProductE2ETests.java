package tests;

import apiSteps.ApiSteps;
import models.CartItemModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import services.SQLRequestSender;
import steps.CartPageSteps;
import steps.MainPageSteps;
import steps.SearchResultsFrameSteps;
import steps.productPagesSteps.ProductDetailsPageSteps;
import templates.BaseTestAfterClassDriverDisposing;
import utils.Utils;

import static com.codeborne.selenide.Condition.exactOwnText;

public class ProductE2ETests extends BaseTestAfterClassDriverDisposing {

    private MainPageSteps mainPageSteps;
    private ProductDetailsPageSteps productDetailsPageSteps;
    private CartPageSteps cartPageSteps;
    private String productFullName;
    private String extendedProductName;

    @BeforeClass
    @Parameters({"validLogin_8", "validPassword_8"})
    public void testSetup(String login, String password) {
        ApiSteps.get().login(login, password)
                .cartApiSteps()
                .deleteAllCartPositionsIfExist();

        var products = ApiSteps.get().catalogApiSteps()
                .getAvailableMobilePhones()
                .getProducts();

        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.productFullName = product.getFullName();
        this.extendedProductName = product.getExtendedName();

        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        SearchResultsFrameSteps searchResultsFrameSteps = this.mainPageSteps
                .searchProduct(this.productFullName);

        searchResultsFrameSteps
                .getPageInstance()
                .getSearchResultItemByName(this.productFullName)
                .getName()
                .shouldHave(exactOwnText(this.extendedProductName));

        this.productDetailsPageSteps = searchResultsFrameSteps.openProductDetailsPageByName(this.productFullName);

        this.productDetailsPageSteps
                .getPageInstance()
                .getProductSummary()
                .getName()
                .shouldHave(exactOwnText(this.extendedProductName));
    }

    @Test(dependsOnMethods = "findProductTest")
    public void addProductToCartTest() {
        this.cartPageSteps = this.productDetailsPageSteps
                .openProductOffersPageThroughPrice()
                .handleFirstVisitLocationPopover()
                .addLowerPriceOfferToCart()
                .openCartPage();

        // DB crutch
        CartItemModel cartItem = this.cartPageSteps
                .getCartItemByName(this.productFullName)
                .getCartItemModel();

        CartItemModel cartItemFromDb = SQLRequestSender.getCartItemByProductName(this.productFullName).get(0);
        Assert.assertEquals(cartItem, cartItemFromDb);
        // DB crutch

        Assert.assertTrue(cartPageSteps.cartItemExist(this.productFullName));
        Assert.assertEquals(cartPageSteps.getPageInstance().getCartItemsNumber(), 1);
    }

    @Test(dependsOnMethods = "addProductToCartTest")
    public void deleteProductFromCartTest(){
        boolean cartItemExist = this.cartPageSteps
                .deleteItemFromCartByName(this.productFullName)
                .cartItemExist(this.productFullName);

        Assert.assertFalse(cartItemExist);
        Assert.assertEquals(this.cartPageSteps.getPageInstance().getDeletedCartItemsNumber(), 1);
    }
}
