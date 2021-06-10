package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private static final By emailInputBy = By.cssSelector("[type=text].auth-input");
    private static final By passwordInputBy = By.cssSelector("[type=password]");
    private static final By loginBtnBy = By.className("auth-button_primary");
    private static final By loginInputErrorBy = By.xpath("//input[@type='text']/ancestor::div[contains(@class, 'auth-form__group')]/descendant::div[contains(@class, 'auth-form__description_error')]");
    private static final By passwordInputErrorBy = By.xpath("//input[@type='password']/following::div[contains(@class, 'auth-form__description_error')]");

    public LoginPage() {
        super(UrlPrefix.CATALOG_PREFIX, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return loginBtnBy;
    }

    public SelenideElement getEmailInput() {
        return $(emailInputBy);
    }

    public SelenideElement getPasswordInput() {
        return $(passwordInputBy);
    }

    public SelenideElement getLoginBtn() {
        return $(loginBtnBy);
    }

    public SelenideElement getLoginInputError(){
        return $(loginInputErrorBy);
    }

    public SelenideElement getPasswordInputError(){
        return $(passwordInputErrorBy);
    }
}
