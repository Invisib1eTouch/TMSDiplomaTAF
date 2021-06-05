package steps;

import baseEntities.BaseStep;
import pages.CommonHeader;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public CommonHeaderSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

}
