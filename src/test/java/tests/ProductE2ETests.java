package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import steps.productPagesSteps.ProductDetailsPageSteps;
import steps.SearchResultsFrameSteps;
import templates.BaseTestAfterClassDriverDisposing;

import static com.codeborne.selenide.Condition.exactOwnText;

public class ProductE2ETests extends BaseTestAfterClassDriverDisposing {

    private MainPageSteps mainPageSteps;
    private ProductDetailsPageSteps productDetailsPageSteps;
    private String productName = "Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";
    private String extendedProductName = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";

    @BeforeClass
    @Parameters({"validLogin_3", "validPassword_3"})
    public void loginBeforeTest(String login, String password) {
        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        // сделали запрос в апи, получили имя
        SearchResultsFrameSteps searchResultsFrameSteps = this.mainPageSteps
                .searchProduct(this.productName);

        searchResultsFrameSteps
                .getPageInstance()
                .getSearchResultItemByName(this.productName)
                .getTitle()
                .shouldHave(exactOwnText(this.extendedProductName));

        this.productDetailsPageSteps = searchResultsFrameSteps.openProductDetailsPageByName(this.productName);

        productDetailsPageSteps
                .getPageInstance()
                .getProductSummary()
                .getTitle()
                .shouldHave(exactOwnText(this.extendedProductName));
    }

    @Test(dependsOnMethods = "findProductTest")
    public void addProductToCartTest() {
        this.productDetailsPageSteps.openProductOffersPageThroughPrice();
    }
}
