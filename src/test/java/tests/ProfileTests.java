package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.profile.ProfileEditPage;
import steps.MainPageSteps;
import steps.profileSteps.ProfileEditPageSteps;
import templates.BaseTestAfterClassDriverDisposing;

import java.util.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ProfileTests extends BaseTestAfterClassDriverDisposing {

//    @Test
//    @Parameters({"validLogin_1", "validPassword_1"})
//    public void changeProfileHeaderBackgroundTest(String login, String password){
//        new MainPageSteps(true)
//                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
//                .openProfileMenu()
//                .openProfilePageOnMainTab();
//    }

//    @Test
//    @Parameters({"validLogin_3", "validPassword_3"})
//    public void dateAndYearEditWithExceedingValuesTest(String login, String password) {
//        String enteredDay = "54";
//        String enteredYear = "5678";
//        String expectedErrorText = "Значение поля не является датой. Значение поля не соответствует формату Y-m-d";
//        String selectedMonthValue = "1";
//
//        new MainPageSteps(true)
//                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
//                .openProfileMenu()
//                .openProfilePageOnMainTab()
//                .openPersonalDataTab()
//                .openEditProfileContainer()
//                .fillDayAndYearOfBirthFields(enteredDay, enteredYear)
//                .saveWithIncorrectDataBtnClick()
//                .getPageInstance()
//                .getErrorLabel()
//                .shouldHave(text(expectedErrorText));
//
//        enteredDay = "21";
//        enteredYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1);
//        expectedErrorText = "Недопустимая дата";
//
//        new ProfileEditPageSteps()
//                .fillDayMonthYearOfBirthFields(enteredDay, selectedMonthValue, enteredYear)
//                .saveWithIncorrectDataBtnClick()
//                .getPageInstance()
//                .getErrorLabel()
//                .shouldHave(text(expectedErrorText));
//
//    }

    @Test
    @Parameters({"validLogin_3", "validPassword_3"})
    public void boundaryValuesTest(String login, String password) {

        ProfileEditPage profileEditPage = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab()
                .openPersonalDataTab()
                .openEditProfileContainer()
                .fillLastNameField(ProfileEditPageSteps.GeneratedStringLengthGreaterThanMaxLength.TRUE)
                .getPageInstance();

        System.out.println(profileEditPage.getLastNameInput().text());

        //Assert.assertEquals(profileEditPage.getLastNameValueLength(), profileEditPage.getEltMaxLength(profileEditPage.getLastNameInput()));
    }

}
