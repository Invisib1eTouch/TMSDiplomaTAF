package steps.commonSteps;

import baseEntities.BaseStep;
import pages.LoginPage;

public class LoginPageSteps extends BaseStep<LoginPage> {

    protected LoginPageSteps() {
        super(false);
    }

    @Override
    public LoginPageSteps openPage() {
        this.page.openAndVerifyCorrectPageOpened();
        return this;
    }
}
