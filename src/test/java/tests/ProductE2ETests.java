package tests;

import apiSteps.ApiSteps;
import io.qameta.allure.Description;
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
    @Parameters({"validLogin_3", "validPassword_3"})
    public void testSetup(String login, String password) {
        ApiSteps.get().setToken(login, password)
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

    @Test(description = "Find product test")
    @Description("Finding product by text and make sure correct product details page is opened.")
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

    @Test(description = "Add product to cart test", dependsOnMethods = "findProductTest")
    @Description("Adding product to cart and make sure correct product is added.")
    public void addProductToCartTest() {
        this.cartPageSteps = this.productDetailsPageSteps
                .openProductOffersPageThroughPrice()
                .handleFirstVisitLocationPopover()
                .addLowestPriceOfferToCart()
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

    @Test(description = "Delete product from cart test", dependsOnMethods = "addProductToCartTest")
    @Description("Removing product from cart and make sure product does not exist in cart.")
    public void deleteProductFromCartTest() {
        boolean cartItemExist = this.cartPageSteps
                .deleteItemFromCartByName(this.productFullName)
                .cartItemExist(this.productFullName);

        Assert.assertFalse(cartItemExist);
        Assert.assertEquals(this.cartPageSteps.getPageInstance().getDeletedCartItemsNumber(), 1);
    }
}