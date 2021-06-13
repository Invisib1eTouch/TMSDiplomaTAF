package tests;

import testData.containers.LoginTestDataContainer;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import testData.StaticProviders;

import static com.codeborne.selenide.Condition.*;

public class LoginTests extends BaseTestAfterMethodDriverDisposing {

    @Test
    @Parameters({"validLogin_2", "validPassword_2", "validUserId_2"})
    public void positiveLoginTest(String login, String password, String userId) {
        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .getPageInstance()
                .getUserId()
                .should(be(visible), have(exactOwnText(userId)));
    }

    @Test(dataProvider = "incorrectLoginData", dataProviderClass = StaticProviders.class, groups = {"csvFiles/invalid_login_test_data.csv"})
    public void negativeLoginTest(LoginTestDataContainer loginTestData) {
        LoginPage loginPage = new MainPageSteps(true)
                .loginWithIncorrectCredentials(loginTestData.getLogin(), loginTestData.getPassword())
                .getPageInstance();
        if (!loginTestData.getLoginFieldError().equals("")) {
            loginPage
                    .getLoginInputError()
                    .should(be(visible),
                            have(ownText(loginTestData.getLoginFieldError())));
        }

        if(!loginTestData.getPasswordFieldError().equals("")){
            loginPage
                    .getPasswordInputError()
                    .should(be(visible),
                            have(ownText(loginTestData.getPasswordFieldError())));
        }
    }
}
