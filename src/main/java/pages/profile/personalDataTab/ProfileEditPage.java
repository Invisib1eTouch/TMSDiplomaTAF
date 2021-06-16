package pages.profile.personalDataTab;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import enums.UrlPrefix;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfileEditPage extends BasePage {

    private static final By saveBtnBy = By.className("auth-button_primary");
    private final String nameFieldsCommonLocator = "input[placeholder='%s']";
    private final String dateOfBirthContainer = "//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[@class='auth-form__row']//input[@maxlength=%s]";
    private static final By monthSelectorBy = By.className("auth-input__real");
    private static final By errorLabelBy = By.cssSelector(".auth-form__description_error");

    public ProfileEditPage() {
        super(UrlPrefix.DEFAULT, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return saveBtnBy;
    }

    public SelenideElement getSaveBtn() {
        return $(saveBtnBy);
    }

    public SelenideElement getLastNameInput() {
        return $(String.format(nameFieldsCommonLocator, "Фамилия"));
    }

    public SelenideElement getFirstNameInput() {
        return $(String.format(nameFieldsCommonLocator, "Имя"));
    }

    public SelenideElement getSurnameInput() {
        return $(String.format(nameFieldsCommonLocator, "Отчество"));
    }

    public SelenideElement getDayOfBirthInput() {
        return $(By.xpath(String.format(dateOfBirthContainer, '2')));
    }

    public SelenideElement getMonthSelector() {
        return $(monthSelectorBy);
    }

    public SelenideElement getYearOfBirthInput() {
        return $(By.xpath(String.format(dateOfBirthContainer, '4')));
    }

    public SelenideElement getErrorLabel() {
        return $(errorLabelBy);
    }

}
