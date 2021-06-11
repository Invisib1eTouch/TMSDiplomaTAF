package pages.profile;

import org.openqa.selenium.By;

public class ProfileMainTab extends ProfileHeader {

    private static final By infoboxBy = By.className("profile-form__part_narrow");

    public ProfileMainTab() {
        super(UrlPrefix.PROFILE_PREFIX, "");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return infoboxBy;
    }
}
