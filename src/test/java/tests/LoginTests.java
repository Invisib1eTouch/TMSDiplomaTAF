package tests;

import testData.containers.LoginTestDataContainer;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import testData.StaticProvider;

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

    @Test(dataProvider = "loginWithIncorrectData", dataProviderClass = StaticProvider.class, groups = {"users.csv"})
    public void negativeLoginTest(LoginTestDataContainer loginTestDataContainer) {
        LoginPage loginPage = new MainPageSteps(true)
                .loginWithIncorrectCredentials(loginTestDataContainer.getLogin(), loginTestDataContainer.getPassword())
                .getPageInstance();
        if (!loginTestDataContainer.getLoginFieldError().equals("")) {
            loginPage
                    .getLoginInputError()
                    .should(be(visible), have(ownText(loginTestDataContainer.getLoginFieldError())));
        }
        if(!loginTestDataContainer.getPasswordFieldError().equals("")){
            loginPage
                    .getPasswordInputError()
                    .should(be(visible), have(ownText(loginTestDataContainer.getPasswordFieldError())));
        }
    }
}
