package pages.profile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.CommonHeader;

import static com.codeborne.selenide.Selenide.$;

public abstract class ProfileHeader extends CommonHeader {

    private static final By personalDataBtnBy = By.cssSelector("[href='/personal']");
    private static final By profileHeaderBackgroundBy = By.className("profile-header__background");
    private static final By editHeaderBackgroundBtnBy = By.className("profile-header__button");

    /**
     * @param path - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public ProfileHeader(String path) {
        super(path);
    }

    public SelenideElement getPersonalDataBtn() {
        return $(personalDataBtnBy);
    }

    public SelenideElement getEditHeaderBackgroundBtn() {
        return $(editHeaderBackgroundBtnBy);
    }

    public SelenideElement getProfileHeaderBackgroundEl(){
        return $(profileHeaderBackgroundBy);
    }
}

