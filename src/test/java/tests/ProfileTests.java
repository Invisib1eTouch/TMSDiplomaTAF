package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import steps.profileSteps.ProfileMainTabSteps;
import templates.BaseTestAfterClassDriverDisposing;

import static com.codeborne.selenide.Condition.attributeMatching;
import static utils.FileHelper.getFileToUpload;

public class ProfileTests extends BaseTestAfterClassDriverDisposing {

    @Test
    @Parameters({"validLogin_1", "validPassword_1"})
    public void changeProfileHeaderBackgroundImageTest(String login, String password) {
        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab()
                .uploadNewProfileHeaderBackground
                        (ProfileMainTabSteps.class, getFileToUpload("this_is_fine_background.png"))
                .getPageInstance()
                .getProfileHeaderBackgroundEl()
                .shouldHave(attributeMatching("style", "background-image: url\\(\".+\"\\);"));
    }
}
