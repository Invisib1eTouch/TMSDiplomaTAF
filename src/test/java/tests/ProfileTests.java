package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterClassDriverDisposing;

public class ProfileTests extends BaseTestAfterClassDriverDisposing {

    @Test
    @Parameters({"validLogin_1", "validPassword_1"})
    public void changeProfileHeaderBackgroundTest(String login, String password){
        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab();
    }
}
