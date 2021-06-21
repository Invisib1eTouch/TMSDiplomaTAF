package pages.productPages.productOffersPage;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import enums.UrlPrefix;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LocationPopover extends BasePage {

    private static final String popupContainerLocator = "//div[@class='offers-list__overlay']/..//div[@class='popover-style__content']";
    private static final String yesBtnLocator = "//*[contains(@class, 'button-style')]";

    public LocationPopover() {
        super(UrlPrefix.DEFAULT, null);
    }

    /**
     * @return - by locator of the element-indicator that correct page is opened
     */
    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return byXpath(popupContainerLocator + yesBtnLocator);
    }

    public SelenideElement getYesBtn(){
        return $(byXpath(popupContainerLocator + yesBtnLocator));
    }

    public SelenideElement getPopupContainer(){
        return $(byXpath(popupContainerLocator));
    }
}