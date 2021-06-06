package steps;

import pages.MainPage;
import steps.commonSteps.CommonHeaderSteps;

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
