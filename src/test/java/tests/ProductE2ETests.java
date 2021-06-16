package tests;

import apiSteps.CatalogApiSteps;
import apiSteps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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
    private String productFullName;
    private String extendedProductName;

    @BeforeClass
    @Parameters({"validLogin_2", "validPassword_2"})
    public void testSetup(String login, String password) {
        var products = CatalogApiSteps.getAvailableMobilePhones().getProducts();
        var product = products.get(Utils.getRandomNumber(0, products.size()));
        this.productFullName = product.getFullName();
        this.extendedProductName = product.getExtendedName();

        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        // сделали запрос в апи, получили имя
        // очистить корзину через апи
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
        var cartPageSteps = this.productDetailsPageSteps
                .openProductOffersPageThroughPrice()
                .handleFirstVisitLocationPopover()
                .addLowerPriceOfferToCart()
                .openCartPage();

        Assert.assertTrue(cartPageSteps.cartItemExist(this.productFullName));
        Assert.assertEquals(cartPageSteps.getPageInstance().getCartItemsNumber(), 1);
    }

    @Test(dependsOnMethods = "addProductToCartTest")
    public void deleteProductFromCartTest(){
        boolean cartItemExist = this.cartPageSteps
                .deleteItemFromCartByName(this.productName)
                .cartItemExist(this.productName);

        Assert.assertFalse(cartItemExist);
        Assert.assertEquals(this.cartPageSteps.getPageInstance().getDeletedCartItemsNumber(), 1);
    }
}
