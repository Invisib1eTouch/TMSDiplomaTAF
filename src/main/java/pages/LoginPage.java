package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private static final By emailInputBy = By.cssSelector("[type=text].auth-input");
    private static final By passwordInputBy = By.cssSelector("[type=password]");
    private static final By loginBtnBy = By.className("auth-button_primary");

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

}
