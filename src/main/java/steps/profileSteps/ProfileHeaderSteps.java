package steps.profileSteps;

import pages.profile.ProfileHeader;
import steps.commonSteps.CommonHeaderSteps;
import steps.profileSteps.personalDataTab.ProfilePersonalDataTabSteps;

public abstract class ProfileHeaderSteps<Page extends ProfileHeader> extends CommonHeaderSteps<Page> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public ProfileHeaderSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    public ProfilePersonalDataTabSteps openPersonalDataTab(){
        this.page.getPersonalDataBtn().click();
        return new ProfilePersonalDataTabSteps(false);
    }
}
