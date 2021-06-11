package steps.profileSteps.personalDataTab;

import baseEntities.BaseStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.profile.personalDataTab.ProfileEditPage;


public class ProfileEditPageSteps extends BaseStep<ProfileEditPage> {

    public ProfileEditPageSteps() {
        super(false);
    }

    public ProfileEditPageSteps fillDayOfBirthField(String value) {
        this.page.getDayOfBirthInput().clear();
        this.page.getDayOfBirthInput().sendKeys(value);
        return this;
    }

    public ProfileEditPageSteps selectMonthOfBirth(Month month) {
        this.page.getMonthSelector().selectOptionByValue(month.getValue());
        return this;
    }

    public ProfileEditPageSteps fillYearOfBirthField(String value) {
        this.page.getYearOfBirthInput().clear();
        this.page.getYearOfBirthInput().sendKeys(value);
        return this;
    }

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

    public ProfileEditPageSteps fillLastNameField(String value) {
        this.page.getLastNameInput().clear();
        this.page.getLastNameInput().sendKeys(value);
        return this;
    }

    public ProfileEditPageSteps saveIncorrectData() {
        this.page.getSaveBtn().click();
        return this;
    }

    public ProfilePersonalDataTabSteps saveCorrectData() {
        this.saveIncorrectData();
        return new ProfilePersonalDataTabSteps(false);
    }

    public ProfileEditPageSteps clearFirstNameField() {
        this.page.getFirstNameInput().clear();
        return this;
    }

    public ProfileEditPageSteps clearPatronymicField() {
        this.page.getSurnameInput().clear();
        return this;
    }

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
