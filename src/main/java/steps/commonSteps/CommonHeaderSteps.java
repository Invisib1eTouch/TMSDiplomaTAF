package steps.commonSteps;

import baseEntities.BaseStep;
import pages.CommonHeader;

import java.lang.reflect.InvocationTargetException;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    protected CommonHeaderSteps(Boolean openPageByUrl) {
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
     *
     * @param callerStepsClass - steps class, that invokes method; necessary to know to maintain invocation chain
     * @param login
     * @param password
     * @param <T> - caller steps class instance
     * @return - any steps class, that extends CommonHeaderSteps class
     */
    public <T extends CommonHeaderSteps<Page>> T loginWithCorrectCredentials(Class<T> callerStepsClass, String login, String password) {
        //    since Login page has not opened yet, it is opening and Login page instance is obtained
        var page = this.openLoginPage().getPageInstance();
        page.getEmailInput().sendKeys(login);
        page.getPasswordInput().sendKeys(password);
        page.getLoginBtn().click();

        return this.getStepsObjectInstance(callerStepsClass, false);
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
        return new ProfileMenuSteps();
    }
}
