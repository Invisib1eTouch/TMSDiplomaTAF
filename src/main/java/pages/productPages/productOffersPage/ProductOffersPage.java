package pages.productPages.productOffersPage;

import models.containers.OfferContainer;
import org.openqa.selenium.By;
import pages.productPages.ProductSummaryPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductOffersPage extends ProductSummaryPage {

    private static final By offersListContainerBy = By.className("offers-list");
    private static final By offerContainerBy = By.className("offers-list__item");
    private static final By offerPriceBy = By.className("offers-list__description_nodecor");
    private static final By offerAddToCartBtnBy = By.cssSelector(".offers-list__part_action .offers-list__button_cart");

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return offersListContainerBy;
    }

    public OfferContainer getLowerPriceOffer() {
        return this.getOffersList()
                .stream()
                .min((offer1, offer2) -> Float.compare(offer1.getFloatPrice(), offer2.getFloatPrice()))
                .orElseThrow();
    }

    public List<OfferContainer> getOffersList() {
        return $$(offerContainerBy)
                .stream()
                .map(offerSelenideEl -> new OfferContainer(
                        offerSelenideEl.$(offerPriceBy),
                        offerSelenideEl.$(offerAddToCartBtnBy)))
                .collect(Collectors.toList());
    }
}
