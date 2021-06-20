package steps.profileSteps.personalDataTab;

import io.qameta.allure.Step;
import pages.profile.personalDataTab.ProfilePersonalDataTab;
import steps.profileSteps.ProfileHeaderSteps;

public class ProfilePersonalDataTabSteps extends ProfileHeaderSteps<ProfilePersonalDataTab> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public ProfilePersonalDataTabSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Step("Open Profile edit page by clicking Edit button.")
    public ProfileEditPageSteps openProfileEditPage() {
        this.page.getEditPersonalDataBtn().hover().click();
        return new ProfileEditPageSteps();
    }
}
