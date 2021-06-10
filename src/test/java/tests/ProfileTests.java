package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.profile.personalDataTab.ProfileEditPage;
import pages.profile.personalDataTab.ProfilePersonalDataTab;
import steps.MainPageSteps;
import steps.profileSteps.personalDataTab.ProfileEditPageSteps;
import steps.profileSteps.personalDataTab.ProfilePersonalDataTabSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import utils.Utils;

import java.util.Calendar;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;


public class ProfileTests extends BaseTestAfterMethodDriverDisposing {

    @Test
    @Parameters({"validLogin_1", "validPassword_1"})
    public void changeProfileHeaderBackgroundTest(String login, String password){
        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab();
    }

    @Test
    @Parameters({"validLogin_4", "validPassword_4"})
    public void dateAndYearEditWithExceedingValuesTest(String login, String password) {
        String enteredDay = "54";
        String enteredYear = "5678";
        String expectedErrorText = "Значение поля не является датой. Значение поля не соответствует формату Y-m-d";

        new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab()
                .openPersonalDataTab()
                .openEditProfilePage()
                .fillDayAndYearOfBirthFields(enteredDay, enteredYear)
                .saveWithIncorrectDataBtnClick()
                .getPageInstance()
                .getErrorLabel()
                .shouldHave(text(expectedErrorText));

        enteredDay = "21";
        enteredYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1);
        expectedErrorText = "Недопустимая дата";

        new ProfileEditPageSteps()
                .fillDayMonthYearOfBirthFields(enteredDay, ProfileEditPageSteps.Month.JAN, enteredYear)
                .saveWithIncorrectDataBtnClick()
                .getPageInstance()
                .getErrorLabel()
                .shouldHave(text(expectedErrorText));

    }

    @Test
    @Parameters({"validLogin_4", "validPassword_4"})
    public void profileEditPageBoundaryValuesTest(String login, String password) {
        final int lastNameMaxLength = 255;
        int enteredLastNameLength = lastNameMaxLength + 1;

        ProfileEditPage profileEditPage = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab()
                .openPersonalDataTab()
                .openEditProfilePage()
                .fillLastNameField(Utils.getRandomAlphaNumericString(enteredLastNameLength))
                .getPageInstance();

        String lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        Assert.assertEquals(profileEditPage.getElementTextLength(profileEditPage.getLastNameInput()),
                lastNameMaxLength);

        ProfilePersonalDataTab profilePersonalDataTab = new ProfileEditPageSteps()
                .saveWithCorrectDataBtnClick()
                .getPageInstance();

        String lastNameTextInProfile = Utils.getPartOfSplitedBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openEditProfilePage()
                .fillLastNameField(Utils.getRandomAlphaNumericString(lastNameMaxLength))
                .getPageInstance();

        lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        Assert.assertEquals(profileEditPage.getElementTextLength(profileEditPage.getLastNameInput()),
                lastNameMaxLength);

        new ProfileEditPageSteps().saveWithCorrectDataBtnClick();

        enteredLastNameLength = lastNameMaxLength - 1;
        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openEditProfilePage()
                .fillLastNameField(Utils.getRandomAlphaNumericString(enteredLastNameLength))
                .getPageInstance();

        lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        Assert.assertEquals(profileEditPage.getElementTextLength(profileEditPage.getLastNameInput()),
                enteredLastNameLength);

        new ProfileEditPageSteps().saveWithCorrectDataBtnClick();

        lastNameTextInProfile = Utils.getPartOfSplitedBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        lastNameTextInProfile = Utils.getPartOfSplitedBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openEditProfilePage()
                .clearFirstNameAndPatronymicFields()
                .fillLastNameField("")
                .getPageInstance();

        lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        Assert.assertEquals(profileEditPage.getElementTextLength(profileEditPage.getLastNameInput()),
                0);

        new ProfileEditPageSteps().saveWithCorrectDataBtnClick();

        Assert.assertEquals(profilePersonalDataTab.getEmptyFullName().getOwnText().replaceAll("\\s+", ""),
                "—");

    }

}
