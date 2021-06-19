package steps.commonSteps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import pages.ProfileMenu;
import steps.profileSteps.ProfileMainTabSteps;

public class ProfileMenuSteps extends BaseStep<ProfileMenu> {

    public ProfileMenuSteps() {
        super(false);
    }

    @Step("Open Profile page on Main tab by clicking user id.")
    public ProfileMainTabSteps openProfilePageOnMainTab(){
        this.page.getUserId().click();
        return new ProfileMainTabSteps(false);
    }

}
