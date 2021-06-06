package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;

import static com.codeborne.selenide.Condition.*;

public class LoginTests extends BaseTestAfterMethodDriverDisposing {

    @Test
    @Parameters({"validLogin", "validPassword"})
    public void positiveLoginTest(String login, String password){
        new MainPageSteps(true)
                .loginWithCorrectCredentials(login, password)
                .openProfileMenu()
                .getPageInstance()
                .getUserId()
                .should(be(visible), have(exactOwnText("3308965")));
    }
}
