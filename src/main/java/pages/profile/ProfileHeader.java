package pages.profile;

import pages.CommonHeader;

public abstract class ProfileHeader extends CommonHeader {
    /**
     * @param path - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public ProfileHeader(String path) {
        super(path);
    }
}
