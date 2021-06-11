package pages.profile;

import org.openqa.selenium.By;

public class ProfileMainTab extends ProfileHeader {

    private static final By infoboxBy = By.className("profile-form__part_narrow");

    public ProfileMainTab() {
        super("");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return infoboxBy;
    }
}
