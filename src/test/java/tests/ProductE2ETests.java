package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CartPage;
import steps.CartPageSteps;
import steps.MainPageSteps;
import steps.SearchResultsFrameSteps;
import steps.productPagesSteps.ProductDetailsPageSteps;
import templates.BaseTestAfterClassDriverDisposing;

import static com.codeborne.selenide.Condition.exactOwnText;

public class ProductE2ETests extends BaseTestAfterClassDriverDisposing {

    private MainPageSteps mainPageSteps;
    private ProductDetailsPageSteps productDetailsPageSteps;
    private CartPageSteps cartPageSteps;
    private String productName = "Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";
    private String extendedProductName = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";

    @BeforeClass
    @Parameters({"validLogin_2", "validPassword_2"})
    public void loginBeforeTest(String login, String password) {
        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        // сделали запрос в апи, получили имя
        // очистить корзину через апи
        SearchResultsFrameSteps searchResultsFrameSteps = this.mainPageSteps
                .searchProduct(this.productName);

        searchResultsFrameSteps
                .getPageInstance()
                .getSearchResultItemByName(this.productName)
                .getName()
                .shouldHave(exactOwnText(this.extendedProductName));

        this.productDetailsPageSteps = searchResultsFrameSteps.openProductDetailsPageByName(this.productName);

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

        Assert.assertTrue(this.cartPageSteps.cartItemExist(this.productName));
        Assert.assertEquals(this.cartPageSteps.getPageInstance().getCartItemsNumber(), 1);
    }

    @Test(dependsOnMethods = "addProductToCartTest")
    public void deleteProductFromCartTest(){
        CartPage cartPage = this.cartPageSteps.deleteItemFromCartByName(this.productName).getPageInstance();
    }
}
