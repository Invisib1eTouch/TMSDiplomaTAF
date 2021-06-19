package steps.commonSteps;

import baseEntities.BaseStep;
import pages.CommonHeader;
import steps.CartPageSteps;
import steps.SearchResultsFrameSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    protected CommonHeaderSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }

    /**
     * @param methodCallerStepsClass - steps class, that invokes method; necessary to know to maintain invocation chain
     * @param login
     * @param password
     * @param <Steps>                - method caller steps class type
     * @return - instance of Steps class that extends CommonHeaderSteps class from which method was invoked
     */
    public <Steps extends CommonHeaderSteps<Page>> Steps loginWithCorrectCredentials(Class<Steps> methodCallerStepsClass, String login, String password) {
        this.login(login, password);
        return this.getStepsObjectInstance(methodCallerStepsClass);
    }

    public LoginPageSteps loginWithIncorrectCredentials(String login, String password) {
        this.login(login, password);
        return new LoginPageSteps();
    }

    public LoginPageSteps openLoginPage() {
        this.page.getLoginBtn().click();
        return new LoginPageSteps();
    }

    public ProfileMenuSteps openProfileMenu() {
        this.page.getProfileIcon().click();
        // Animation of profile menu opening takes 200ms
        sleep(200);
        return new ProfileMenuSteps();
    }

    /**
     * @param login
     * @param password
     * @implNote - due to the fact that header is presented on all pages this method is necessary
     */
    private void login(String login, String password) {
//    since Login page has not opened yet, it is opening and Login page instance is obtained
        var page = this.openLoginPage().getPageInstance();
        page.getEmailInput().sendKeys(login);
        page.getPasswordInput().sendKeys(password);
        page.getLoginBtn().click();
        page.getLoginBtnLoaderBy().shouldNot(exist);
    }

    public SearchResultsFrameSteps searchProduct(String searchQuery) {
        this.page.getSearchInput().sendKeys(searchQuery);
        switchTo().frame(this.page.getSearchResultsIFrame());
        return new SearchResultsFrameSteps().waitLoadingFinished();
    }

    public CartPageSteps openCartPage(){
        this.page.getCartBtnBy().click();
        return new CartPageSteps(false);
    }
}