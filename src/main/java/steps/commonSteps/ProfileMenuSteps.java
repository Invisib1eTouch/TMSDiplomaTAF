package steps.commonSteps;

import baseEntities.BaseStep;
import pages.ProfileMenu;
import steps.profileSteps.ProfileMainTabSteps;

public class ProfileMenuSteps extends BaseStep<ProfileMenu> {

    public ProfileMenuSteps() {
        super(false);
    }

    public ProfileMainTabSteps openProfilePageOnMainTab(){
        this.page.getUserId().click();
        return new ProfileMainTabSteps(false);
    }

}
