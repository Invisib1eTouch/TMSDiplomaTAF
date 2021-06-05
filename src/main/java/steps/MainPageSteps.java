package steps;

import baseEntities.BaseStep;
import pages.CommonHeader;
import pages.MainPage;

public class MainPageSteps extends CommonHeaderSteps<MainPage> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public MainPageSteps(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    public MainPageSteps openPage() {
        this.page.openAndVerifyCorrectPageOpened();
        return this;
    }

}
