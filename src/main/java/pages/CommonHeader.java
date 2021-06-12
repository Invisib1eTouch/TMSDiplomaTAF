package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class CommonHeader extends BasePage {

    private static final By loginBtnBy = By.className("auth-bar__item--text");
    private static final By profileIconBy = By.className("b-top-profile__preview");

    /**
     * @param urlPrefix - prefix for the page e.g. catalog, profile, etc.
     * @param path      - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public CommonHeader(UrlPrefix urlPrefix, String path) {
        super(urlPrefix, path);
    }

    public SelenideElement getLoginBtn() {
        return $(loginBtnBy);
    }

    public SelenideElement getProfileIcon() {
        return $(profileIconBy);
    }
}
