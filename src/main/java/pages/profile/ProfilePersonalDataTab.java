package pages.profile;

import org.openqa.selenium.By;

public class ProfilePersonalDataTab extends ProfileHeader {

    private static final By emailLabelBy = By.cssSelector(".profile-form__set-item_email .profile-form__label-title");
    private static final By editPersonalDataBtnBy = By.cssSelector(".profile-form__set-item_person .profile-form__link_base-alter");

    public ProfilePersonalDataTab() {
        super("");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return emailLabelBy;
    }
}
