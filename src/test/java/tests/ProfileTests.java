package tests;

import apiSteps.ApiSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.profile.personalDataTab.ProfileEditPage;
import pages.profile.personalDataTab.ProfilePersonalDataTab;
import steps.MainPageSteps;
import steps.profileSteps.ProfileMainTabSteps;
import steps.profileSteps.personalDataTab.ProfileEditPageSteps;
import steps.profileSteps.personalDataTab.ProfilePersonalDataTabSteps;
import templates.BaseTestAfterMethodDriverDisposing;
import utils.Utils;

import java.util.Calendar;
import java.util.Objects;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.exactOwnText;
import static utils.FileHelper.getFileToUpload;


public class ProfileTests extends BaseTestAfterMethodDriverDisposing {

    private static final String loginForTest = "validLogin_4";
    private static final String passwordForTest = "validPassword_4";

    private ProfileMainTabSteps profileMainTabSteps;

    @BeforeMethod
    @Parameters({loginForTest, passwordForTest})
    public void loginBeforeTest(String login, String password) {
        this.profileMainTabSteps = new MainPageSteps(true)
                .loginWithCorrectCredentials(MainPageSteps.class, login, password)
                .openProfileMenu()
                .openProfilePageOnMainTab();
    }

    @AfterClass(alwaysRun = true)
    @Parameters({loginForTest, passwordForTest})
    public void testClassTeardown(String login, String password) {
        ApiSteps.get()
                .setToken(login, password)
                .userApiSteps()
                .removeProfileHeaderCoverImage();
    }

    @Test(description = "Change profile background header image test")
    @Description("Changing background image in user profile and make sure new image is applied.")
    public void changeProfileHeaderBackgroundImageTest() {
        this.profileMainTabSteps
                .uploadNewProfileHeaderBackground
                        (ProfileMainTabSteps.class, getFileToUpload("this_is_fine_background.png"))
                .getPageInstance()
                .getProfileHeaderBackgroundEl()
                .shouldHave(attributeMatching("style", "background-image: url\\(\".+\"\\);"));
    }

    @Test(description = "Edit birth day and year with exceeding values test")
    @Description("Editing day and year with future values and make sure error message is displayed.")
    public void editDayAndYearWithExceedingValuesTest() {
        // Case: both day and year have exceeding values
        String enteredDay = "54";
        String enteredYear = "5678";
        String expectedErrorText = "Значение поля не является датой. Значение поля не соответствует формату Y-m-d";

        this.profileMainTabSteps
                .openPersonalDataTab()
                .openProfileEditPage()
                .fillDateOfBirthFields(enteredDay, null, enteredYear)
                .saveIncorrectData()
                .getPageInstance()
                .getErrorLabel()
                .shouldHave(exactOwnText(expectedErrorText));

        // Case: year is in the future
        enteredDay = "21";
        enteredYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1);
        expectedErrorText = "Недопустимая дата";

        new ProfileEditPageSteps()
                .fillDateOfBirthFields(enteredDay, ProfileEditPageSteps.Month.JAN, enteredYear)
                .saveIncorrectData()
                .getPageInstance()
                .getErrorLabel()
                .shouldHave(exactOwnText(expectedErrorText));
    }

    @Test(description = "Last Name field boundary test")
    @Description("Verification of Last Name field filling with boundary values.")
    public void lastNameFieldBoundaryValuesTest() {
        final int lastNameMaxLength = 255;

        // CASE: Entering to the field a string with length that exceeds the max length
        int enteredLastNameLength = lastNameMaxLength + 1;
        String generatedBySizeString = Utils.getRandomAlphaNumericString(enteredLastNameLength);

        ProfileEditPage profileEditPage = this.profileMainTabSteps
                .openPersonalDataTab()
                .openProfileEditPage()
                .fillLastNameField(generatedBySizeString)
                .getPageInstance();

        String lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        // Verification that entered string was cut by max  field's length
        Assert.assertEquals(this.getElementTextLength(profileEditPage.getLastNameInput()),
                lastNameMaxLength);

        ProfilePersonalDataTab profilePersonalDataTab = new ProfileEditPageSteps()
                .saveCorrectData()
                .getPageInstance();

        String lastNameTextInProfile = Utils.getPartOfSplitBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);

        // Verification that after saving last name has correct text after cutting by max length on the editing page
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        // CASE: Entering to the field a value with length that is equals to the max length
        generatedBySizeString = Utils.getRandomAlphaNumericString(lastNameMaxLength);
        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openProfileEditPage()
                .fillLastNameField(generatedBySizeString)
                .getPageInstance();

        lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        // Verification that entered string was cut by max field's length
        Assert.assertEquals(this.getElementTextLength(profileEditPage.getLastNameInput()),
                lastNameMaxLength);

        new ProfileEditPageSteps().saveCorrectData();

        lastNameTextInProfile = Utils.getPartOfSplitBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);

        // Verification that after saving last name has correct text after cutting by max length on the editing page
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        // CASE: Entering to the field a value with length that is less than the max length
        enteredLastNameLength = lastNameMaxLength - 1;
        generatedBySizeString = Utils.getRandomAlphaNumericString(enteredLastNameLength);
        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openProfileEditPage()
                .fillLastNameField(generatedBySizeString)
                .getPageInstance();

        lastNameEditProfileText = profileEditPage.getLastNameInput().getValue();

        // Verification that entered string was cut by max  field's length
        Assert.assertEquals(this.getElementTextLength(profileEditPage.getLastNameInput()),
                enteredLastNameLength);

        new ProfileEditPageSteps().saveCorrectData();

        lastNameTextInProfile = Utils.getPartOfSplitBySpacesText(Objects.requireNonNull(profilePersonalDataTab.getFullName().getOwnText()), 0);

        // Verification that after saving last name has correct text after cutting by max length on the editing page
        Assert.assertEquals(lastNameTextInProfile, lastNameEditProfileText);

        // CASE: Entering to the field an empty value
        profileEditPage = new ProfilePersonalDataTabSteps(false)
                .openProfileEditPage()
                .clearFirstNameAndPatronymicFields()
                .fillLastNameField("")
                .getPageInstance();

        Assert.assertEquals(this.getElementTextLength(profileEditPage.getLastNameInput()),
                0);

        new ProfileEditPageSteps().saveCorrectData();

        Assert.assertEquals(profilePersonalDataTab.getEmptyFullName().getOwnText().replaceAll("\\s+", ""),
                "—");
    }

    @Test(description = "Edit birth Day test")
    @Description("Editing birth Day with incorrect value and make sure changes are not applied and error message is displayed.")
    public void editDayOfBirthWithTextValueIssueTest() {
        // CASE: when user enters letters into the day of birth field,
        // there should be a validation error (same as for year, for consistency) and no changes are saved,
        // but now changes are saved and letters value is replaced with '1' on the Personal Data page

        String enteredInvalidDay = "hgtu";
        String enteredValidYear = "1997";
        String expectedErrorText = "Недопустимая дата";

        this.profileMainTabSteps
                .openPersonalDataTab()
                .openProfileEditPage()
                .fillDateOfBirthFields(enteredInvalidDay, ProfileEditPageSteps.Month.APR, enteredValidYear)
                .saveIncorrectData()
                .getPageInstance()
                .getErrorLabel()
                .shouldHave(exactOwnText(expectedErrorText));
    }
}