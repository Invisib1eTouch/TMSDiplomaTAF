package steps.profileSteps.personalDataTab;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.profile.personalDataTab.ProfileEditPage;


public class ProfileEditPageSteps extends BaseStep<ProfileEditPage> {

    public ProfileEditPageSteps() {
        super(false);
    }

    @Step("Filling the Day of Birth field with '{value}' value.")
    public ProfileEditPageSteps fillDayOfBirthField(String value) {
        this.page.getDayOfBirthInput().clear();
        this.page.getDayOfBirthInput().sendKeys(value);
        return this;
    }

    @Step("Selecting month of birth with '{month}' value.")
    public ProfileEditPageSteps selectMonthOfBirth(Month month) {
        this.page.getMonthSelector().selectOptionByValue(month.getValue());
        return this;
    }

    @Step("Filling the Year of Birth field with '{value}' value.")
    public ProfileEditPageSteps fillYearOfBirthField(String value) {
        this.page.getYearOfBirthInput().clear();
        this.page.getYearOfBirthInput().sendKeys(value);
        return this;
    }

    @Step("Filling date of birth fields with day: '{day}', month: '{month}', year: '{year}'.")
    public ProfileEditPageSteps fillDateOfBirthFields(String day, Month month, String year) {
        if (day != null) {
            this.fillDayOfBirthField(day);
        }
        if (month != null) {
            this.selectMonthOfBirth(month);
        }
        if (year != null) {
            this.fillYearOfBirthField(year);
        }

        return this;
    }

    @Step("Filling Last Name field with '{value}' data.")
    public ProfileEditPageSteps fillLastNameField(String value) {
        this.page.getLastNameInput().clear();
        this.page.getLastNameInput().sendKeys(value);
        return this;
    }

    @Step("Saving incorrect data by clicking Save button.")
    public ProfileEditPageSteps saveIncorrectData() {
        this.page.getSaveBtn().click();
        return this;
    }

    @Step("Saving correct data by clicking Save button.")
    public ProfilePersonalDataTabSteps saveCorrectData() {
        this.saveIncorrectData();
        return new ProfilePersonalDataTabSteps(false);
    }

    @Step("Clearing First Name field.")
    public ProfileEditPageSteps clearFirstNameField() {
        this.page.getFirstNameInput().clear();
        return this;
    }

    @Step("Clearing Patronymic field")
    public ProfileEditPageSteps clearPatronymicField() {
        this.page.getSurnameInput().clear();
        return this;
    }

    @Step("Clearing First Name and Patronymic fields.")
    public ProfileEditPageSteps clearFirstNameAndPatronymicFields() {
        this.clearFirstNameField();
        this.clearPatronymicField();
        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum Month {
        JAN("0"),
        FEB("1"),
        MAR("2"),
        APR("3"),
        MAY("4"),
        JUN("5"),
        JUL("6"),
        AUG("7"),
        SEP("8"),
        OCT("9"),
        NOV("10"),
        DEC("11");

        private final String value;
    }
}
