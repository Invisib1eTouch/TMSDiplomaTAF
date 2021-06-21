package steps.profileSteps;

import pages.profile.ProfileMainTab;
import steps.commonSteps.CommonHeaderSteps;

public class ProfileMainTabSteps extends ProfileHeaderSteps<ProfileMainTab> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public ProfileMainTabSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }
}