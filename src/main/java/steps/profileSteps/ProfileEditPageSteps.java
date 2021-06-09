package steps.profileSteps;

import baseEntities.BaseStep;
import org.openqa.selenium.NoSuchElementException;
import pages.profile.ProfileEditPage;
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

    public ProfileEditPageSteps selectMonthOfBirthByValue(String value) {
        try {
            this.page.getMonthSelector().selectOptionByValue(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("There's no option with such value:  " + value);
        }
        return this;
    }

    public ProfileEditPageSteps selectMonthOfBirthByOptionText(String optionText) {
        try {
            this.page.getMonthSelector().selectOption(optionText);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("There's no option with such text: " + optionText);
        }
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

    public ProfileEditPageSteps fillDayMonthYearOfBirthFields(String day, String monthSelectValue, String year) {
        this.fillDayOfBirthField(day);
        this.selectMonthOfBirthByValue(monthSelectValue);
        this.fillYearOfBirthField(year);
        return this;
    }

    public enum GeneratedStringLengthGreaterThanMaxLength {
        TRUE,
        FALSE,
        NULL
    }

    public ProfileEditPageSteps fillLastNameField(GeneratedStringLengthGreaterThanMaxLength generatedStringLengthGreaterThanMaxLength) {
        int maxStringLength = this.page.getEltMaxLength(this.page.getLastNameInput());
        this.page.getLastNameInput().clear();
        switch (generatedStringLengthGreaterThanMaxLength) {
            case NULL -> this.page.getLastNameInput().sendKeys(Utils.getRandomAlphaNumericString(maxStringLength));
            case TRUE -> this.page.getLastNameInput().sendKeys(Utils.getRandomAlphaNumericString(maxStringLength + 1));
            case FALSE -> this.page.getLastNameInput().sendKeys(Utils.getRandomAlphaNumericString(maxStringLength - 1));
            default -> throw new ArrayIndexOutOfBoundsException("No maxlength value was found for the string or it's 0");
        }
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

}
