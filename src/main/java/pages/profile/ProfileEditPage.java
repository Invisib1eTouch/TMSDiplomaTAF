package pages.profile;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class ProfileEditPage extends BasePage {

    private static final By saveBtnBy = By.className("auth-button_primary");
    private static final By lastNameInputBy = By.cssSelector("input[placeholder='Фамилия']");
    private static final By dayOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[@maxlength=2]");
    private static final By monthSelectorBy = By.className("auth-input__real");
    private static final By yearOfBirthInputBy = By.xpath("//div[normalize-space()='Дата рождения']" +
            "/ancestor::div[contains(@class, 'auth-form__row')]//input[@maxlength=4]");
    private static final By errorLabelBy = By.cssSelector(".auth-form__description_error ");

    public ProfileEditPage() {
        super(UrlPrefix.CATALOG_PREFIX, null);
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

    public int getEltMaxLength(SelenideElement selenideElement){
        return Integer.parseInt(Objects.requireNonNull(selenideElement.getAttribute("maxlength")));
    }

    public int getLastNameValueLength(){
        return this.getLastNameInput().text().length();
    }
}
