package pages.profile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.CommonHeader;

import static com.codeborne.selenide.Selenide.$;

public abstract class ProfileHeader extends CommonHeader {

    private static final By personalDataBtnBy = By.className("[href='/personal']");

    /**
     * @param path - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public ProfileHeader(String path) {
        super(path);
    }

    public SelenideElement getPersonalDataBtn(){
        return $(personalDataBtnBy);
    }
}
