package steps.profileSteps;

import pages.profile.ProfileMainTab;
import pages.profile.ProfilePersonalDataTab;
import steps.commonSteps.CommonHeaderSteps;

public class ProfilePersonalDataTabSteps extends ProfileHeaderSteps<ProfilePersonalDataTab> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    protected ProfilePersonalDataTabSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    public ProfileEditPageSteps openEditProfileContainer(){
        this.page.getEditPersonalDataBtn().hover().click();
        return new ProfileEditPageSteps();
    }
}
