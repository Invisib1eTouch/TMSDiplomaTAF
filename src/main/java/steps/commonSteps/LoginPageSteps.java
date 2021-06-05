package steps;

import baseEntities.BaseStep;
import pages.LoginPage;

public class LoginPageSteps extends BaseStep<LoginPage> {

    private LoginPageSteps() {
        super(false);
    }

    @Override
    public LoginPageSteps openPage() {
        this.page.openAndVerifyCorrectPageOpened();
        return this;
    }

    public void loginWithCorrectCredentials(String email, String password) {
        this.page.getEmailInput().sendKeys(email);
        this.page.getPasswordInput().sendKeys(password);
        this.page.getLoginBtn().click();
    }
}
