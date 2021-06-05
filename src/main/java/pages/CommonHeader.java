package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public abstract class CommonHeader extends BasePage {

    private static final By loginBtnBy = By.className("auth-bar__item--text");

    /**
     * @param path - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public CommonHeader(String path) {
        super(path);
    }


    public SelenideElement getLoginBtn(){
        return $(loginBtnBy);
    }

}
