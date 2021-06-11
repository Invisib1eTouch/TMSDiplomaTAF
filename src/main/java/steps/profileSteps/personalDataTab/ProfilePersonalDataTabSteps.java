package steps.profileSteps.personalDataTab;

import pages.profile.personalDataTab.ProfilePersonalDataTab;
import steps.profileSteps.ProfileHeaderSteps;

public class ProfilePersonalDataTabSteps extends ProfileHeaderSteps<ProfilePersonalDataTab> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public ProfilePersonalDataTabSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    public ProfileEditPageSteps openProfileEditPage() {
        this.page.getEditPersonalDataBtn().hover().click();
        return new ProfileEditPageSteps();
    }
}
