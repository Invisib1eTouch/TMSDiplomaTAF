package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;

import static com.codeborne.selenide.Condition.*;

public class LoginTests extends BaseTestAfterMethodDriverDisposing {

    @Test
    @Parameters({"validLogin", "validPassword", "validUserId"})
    public void positiveLoginTest(String login, String password, String userId){
        new MainPageSteps(true)
                .loginWithCorrectCredentials(login, password)
                .openProfileMenu()
                .getPageInstance()
                .getUserId()
                .should(be(visible), have(exactOwnText(userId)));
    }
}
