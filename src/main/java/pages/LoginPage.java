package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import enums.UrlPrefix;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private static final String emailInputLocator = "//*[@type='text' and contains(@class,'auth-input')]";
    private static final String passwordInputLocator = "//*[@type='password']";
    private static final String authFieldErrorMesLocator = "following::div[contains(@class, 'auth-form__description_error')]";
    private static final By loginBtnBy = By.className("auth-button_primary");
    private static final By loginBtnLoaderBy = By.className("auth-button_animated");

    public LoginPage() {
        super(UrlPrefix.DEFAULT, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return loginBtnBy;
    }

    public SelenideElement getEmailInput() {
        return $(byXpath(emailInputLocator));
    }

    public SelenideElement getPasswordInput() {
        return $(byXpath(passwordInputLocator));
    }

    public SelenideElement getLoginBtn() {
        return $(loginBtnBy);
    }

    public SelenideElement getLoginInputError() {
        return $(byXpath(emailInputLocator + "/" + authFieldErrorMesLocator));
    }

    public SelenideElement getPasswordInputError() {
        return $(byXpath(passwordInputLocator + "/" + authFieldErrorMesLocator));
    }

    public SelenideElement getLoginBtnLoaderBy() {
        return $(loginBtnLoaderBy);
    }
}