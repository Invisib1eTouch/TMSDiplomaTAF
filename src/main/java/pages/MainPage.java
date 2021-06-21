package pages;

import enums.UrlPrefix;
import org.openqa.selenium.By;

public class MainPage extends CommonHeader {

    private static final By pageTitleBy = By.xpath("//h1[@class='catalog-navigation__title']");

    public MainPage() {
        super(UrlPrefix.CATALOG, "");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return pageTitleBy;
    }

}