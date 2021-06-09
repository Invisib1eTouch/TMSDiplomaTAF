package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.MainPageSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import testData.StaticProviderFromCsvFile;

import static com.codeborne.selenide.Condition.*;

public class LoginTests extends BaseTestAfterMethodDriverDisposing {

    @Test
    @Parameters({"validLogin_2", "validPassword_2", "validUserId_2"})
    public void positiveLoginTest(String login, String password, String userId){
        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .getPageInstance()
                .getUserId()
                .should(be(visible), have(exactOwnText(userId)));
    }

//    @Test(dataProvider = "userDetails", dataProviderClass = StaticProviderFromCsvFile.class)
//    public void negativeLoginTest(String login, String password){
//        new MainPageSteps(true)
//                .loginWithIncorrectCredentials(login, password);
//        sleep(3000);
//        System.out.println(login + " " + password);
//    }

    @Test(dataProvider = "readFromCsvFile", dataProviderClass = StaticProviderFromCsvFile.class, groups = {"users.csv"})
    public void negativeLoginTest(String login, String password){
//        new MainPageSteps(true)
//                .loginWithIncorrectCredentials(login, password);
//        sleep(3000);
        System.out.println(login + " " + password);
    }
}
