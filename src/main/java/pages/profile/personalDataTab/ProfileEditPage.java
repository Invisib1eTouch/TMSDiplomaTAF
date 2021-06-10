package pages.profile.personalDataTab;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class ProfileEditPage extends BasePage {

    private static final By saveBtnBy = By.className("auth-button_primary");
    private static final By lastNameInputBy = By.cssSelector("input[placeholder='Фамилия']");
    private static final By firstNameInputBy = By.cssSelector("input[placeholder='Имя']");
    private static final By patronymicInputBy = By.cssSelector("input[placeholder='Отчество']");
    private static final By dayOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[@maxlength=2]");
    private static final By monthSelectorBy = By.className("auth-input__real");
    private static final By yearOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[@maxlength=4]");
    private static final By errorLabelBy = By.cssSelector(".auth-form__description_error ");

    public ProfileEditPage() {
        super(UrlPrefix.PROFILE_PREFIX, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return saveBtnBy;
    }

    public SelenideElement getSaveBtn() {
        return $(saveBtnBy);
    }

    public SelenideElement getLastNameInput() {
        return $(lastNameInputBy);
    }

    public SelenideElement getFirstNameInput() {
        return $(firstNameInputBy);
    }

    public SelenideElement getPatronymicInput() {
        return $(patronymicInputBy);
    }

    public SelenideElement getDayOfBirthInput() {
        return $(dayOfBirthInputBy);
    }

    public SelenideElement getMonthSelector() {
        return $(monthSelectorBy);
    }

    public SelenideElement getYearOfBirthInput() {
        return $(yearOfBirthInputBy);
    }

    public SelenideElement getErrorLabel(){
        return $(errorLabelBy);
    }

}
