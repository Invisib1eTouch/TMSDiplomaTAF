package tests;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import steps.ProductDetailsPageSteps;
import steps.SearchResultsFrameSteps;
import templates.BaseTestAfterClassDriverDisposing;

import static com.codeborne.selenide.Condition.exactOwnText;

public class ProductE2ETests extends BaseTestAfterClassDriverDisposing {

    private MainPageSteps mainPageSteps;

    @BeforeClass
    @Parameters({"validLogin_3", "validPassword_3"})
    public void loginBeforeTest(String login, String password) {
        this.mainPageSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password);
    }

    @Test
    public void findProductTest() {
        // сделали запрос в апи, получили имя
        String productName = "Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";
        String productExtendedName = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (синий)";
        SearchResultsFrameSteps searchResultsFrameSteps = this.mainPageSteps
                .searchProduct(productName);

        searchResultsFrameSteps
                .getPageInstance()
                .getSearchResultItemByName(productName)
                .getTitle()
                .shouldHave(exactOwnText(productExtendedName));

        ProductDetailsPageSteps productDetailsPageSteps =  searchResultsFrameSteps.openProductDetailsPageByName(productName);

        productDetailsPageSteps
                .getPageInstance()
                .getProductDetails()
                .getTitle()
                .shouldHave(exactOwnText(productExtendedName));
    }
}
