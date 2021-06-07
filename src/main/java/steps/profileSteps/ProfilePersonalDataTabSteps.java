package steps.profileSteps;

import pages.profile.ProfileMainTab;
import steps.commonSteps.CommonHeaderSteps;

public class ProfilePersonalDataTabSteps extends ProfileHeaderSteps<ProfileMainTab> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    protected ProfilePersonalDataTabSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    public ProfilePersonalDataTabSteps openPage() {
        this.page.openAndVerifyCorrectPageOpened();
        return this;
    }

    @Override
// Not implemented since will be changed to another architecture type
    public CommonHeaderSteps<ProfileMainTab> loginWithCorrectCredentials(String login, String password) {
        return null;
    }
}
