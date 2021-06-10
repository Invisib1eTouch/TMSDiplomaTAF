package steps.profileSteps.personalDataTab;

import baseEntities.BaseStep;
import com.codeborne.selenide.ex.SoftAssertionError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import pages.profile.personalDataTab.ProfileEditPage;
import utils.Utils;


public class ProfileEditPageSteps extends BaseStep<ProfileEditPage> {

    public ProfileEditPageSteps() {
        super(false);
    }

    public ProfileEditPageSteps fillDayOfBirthField(String value) {
        this.page.getDayOfBirthInput().clear();
        this.page.getDayOfBirthInput().sendKeys(value);
        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum Month{
        JAN ("0"),
        FEB ("1"),
        MAR ("2"),
        APR ("3"),
        MAY ("4"),
        JUN ("5"),
        JUL ("6"),
        AUG ("7"),
        SEP ("8"),
        OCT ("9"),
        NOV ("10"),
        DEC ("11");

        private final String value;
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

    public ProfileEditPageSteps fillDayAndYearOfBirthFields(String day, String year) {
        this.fillDayOfBirthField(day);
        this.fillYearOfBirthField(year);
        return this;
    }

    public ProfileEditPageSteps fillDayMonthYearOfBirthFields(String day, Month monthSelectValue, String year) {
        this.fillDayOfBirthField(day);
        this.selectMonthOfBirth(monthSelectValue);
        this.fillYearOfBirthField(year);
        return this;
    }

    public ProfileEditPageSteps fillLastNameField(String value) {
        this.page.getLastNameInput().clear();
        this.page.getLastNameInput().sendKeys(value);
        return this;
    }

    public ProfileEditPageSteps saveWithIncorrectDataBtnClick() {
        this.page.getSaveBtn().click();
        return this;
    }

    public ProfilePersonalDataTabSteps saveWithCorrectDataBtnClick() {
        this.page.getSaveBtn().click();
        return new ProfilePersonalDataTabSteps(false);
    }

    public ProfileEditPageSteps clearFirstNameField(){
        this.page.getFirstNameInput().clear();
        return this;
    }

    public ProfileEditPageSteps clearPatronymicField(){
        this.page.getPatronymicInput().clear();
        return this;
    }

    public ProfileEditPageSteps clearFirstNameAndPatronymicFields(){
        this.clearFirstNameField();
        this.clearPatronymicField();
        return this;
    }

}
