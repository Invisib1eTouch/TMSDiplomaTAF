package pages.profile.personalDataTab;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.profile.ProfileHeader;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePersonalDataTab extends ProfileHeader {

    private static final By emailLabelBy = By.cssSelector(".profile-form__set-item_email .profile-form__label-title");
    private static final By editPersonalDataBtnBy = By.cssSelector(".profile-form__set-item_person .profile-form__link_base-alter");
    private static final String fullNameCommonLocator = "//div[contains(@class,'profile-form__label-title') and normalize-space()='ФИО']" +
            "/ancestor::div[contains(@class,'profile-form__group')]//";
    private static final String fullNameNotEmptyLocator = "span";
    private static final String fullNameEmptyLocator = "div[contains(@class, 'profile-form__hint')]";

    public ProfilePersonalDataTab() {
        super("/personal");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return emailLabelBy;
    }

    public SelenideElement getEditPersonalDataBtn() {
        return $(editPersonalDataBtnBy);
    }

    public SelenideElement getFullName() {
        return $(By.xpath(fullNameCommonLocator + fullNameNotEmptyLocator));
    }

    public SelenideElement getEmptyFullName() {
        return $(By.xpath(fullNameCommonLocator + fullNameEmptyLocator));
    }
}