package pages.productPages.productOffersPage;

import lombok.extern.slf4j.Slf4j;
import models.containers.OfferContainer;
import org.openqa.selenium.By;
import pages.productPages.ProductSummaryPage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class ProductOffersPage extends ProductSummaryPage {

    private static final By offersListContainerBy = By.className("offers-list");
    private static final By offerContainerBy = By.className("offers-list__item");
    private static final By offerPriceBy = By.className("offers-list__description_nodecor");
    private static final By offerAddToCartBtnBy = By.cssSelector(".offers-list__part_action .offers-list__button_cart");
    private static final By offerProceedToCartBtnBy = By.cssSelector(".offers-list__part_action .button-style_another");

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return offersListContainerBy;
    }

    public OfferContainer getLowestPriceOffer() {
        try{
            return this.getOffersList()
                    .stream()
                    .min((offer1, offer2) -> Float.compare(offer1.getFloatPrice(), offer2.getFloatPrice()))
                    .orElseThrow();
        } catch (NoSuchElementException e){
            var errMes = "Couldn't find offer with the lowest price. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new NoSuchElementException(errMes);
        }
    }

    public List<OfferContainer> getOffersList() {
        return $$(offerContainerBy)
                .stream()
                .map(offerSelenideEl -> new OfferContainer(
                        offerSelenideEl.$(offerPriceBy),
                        offerSelenideEl.$(offerAddToCartBtnBy)))
                .collect(Collectors.toList());
    }

    /**
     * @return - LocationPopover
     * @implNote - returns Location popover that appears ONLY on first entering on the page
     */
    public LocationPopover getLocationPopoverOnFirstPageVisit() {
        $(offersListContainerBy).scrollTo();
        LocationPopover locationPopover = new LocationPopover();
        locationPopover.verifyCorrectPageOpened();
        return locationPopover;
    }
}