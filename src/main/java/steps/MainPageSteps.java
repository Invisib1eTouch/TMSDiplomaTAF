package steps;

import pages.MainPage;
import steps.commonSteps.CommonHeaderSteps;

public class MainPageSteps extends CommonHeaderSteps<MainPage> {

    /**
     * @param openPageByUrl - if true page will be opened by url
     */
    public MainPageSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }
}
