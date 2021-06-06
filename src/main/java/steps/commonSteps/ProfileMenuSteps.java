package steps.commonSteps;

import baseEntities.BaseStep;
import pages.ProfileMenu;

public class ProfileMenuSteps extends BaseStep<ProfileMenu> {

    public ProfileMenuSteps() {
        super(false);
    }

    @Override
    public ProfileMenuSteps openPage() {
        this.page.verifyCorrectPageOpened();
        return this;
    }
}
