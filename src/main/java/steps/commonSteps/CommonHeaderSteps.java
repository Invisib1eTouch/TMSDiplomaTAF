package steps.commonSteps;

import baseEntities.BaseStep;
import pages.CommonHeader;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    protected CommonHeaderSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    /**
     * @param login
     * @param password
     * @implNote - due to the fact that header is presented on all pages this method is necessary
     */
    protected void login(String login, String password) {
//    since Login page has not opened yet, it is opening and Login page instance is obtained
        var page = this.openLoginPage().getPageInstance();
        page.getEmailInput().sendKeys(login);
        page.getPasswordInput().sendKeys(password);
        page.getLoginBtn().click();
    }

    /**
     * @param login
     * @param password
     * @return - steps type where method is implemented
     * @implNote return type has to be changed during implementation to corresponding steps class to save chain of
     * invocation
     * e.g. from:
     * public CommonHeaderSteps<Page> loginWithCorrectCredentials(String login, String password);
     * to:
     * public MainPageSteps loginWithCorrectCredentials(String login, String password);
     * each overridden method body should contain:
     * this.login(login, password);
     * return this;
     */
    public abstract CommonHeaderSteps<Page> loginWithCorrectCredentials(String login, String password);

    public LoginPageSteps loginWithIncorrectCredentials(String login, String password) {
        this.login(login, password);
        return new LoginPageSteps();
    }

    public LoginPageSteps openLoginPage() {
        this.page.getLoginBtn().click();
        return new LoginPageSteps();
    }
}
