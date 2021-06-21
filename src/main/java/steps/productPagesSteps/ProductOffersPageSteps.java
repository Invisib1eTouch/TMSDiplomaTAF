package steps.productPagesSteps;

import io.qameta.allure.Step;
import pages.productPages.productOffersPage.ProductOffersPage;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    @Step("Add offer with the lowest price to the cart.")
    public ProductOffersPageSteps addLowestPriceOfferToCart() {
        this.page.getLowestPriceOffer().getAddToCartBtn().click();
        return this;
    }

    @Step("Accept location popup that is displayed on first page visiting.")
    public ProductOffersPageSteps handleFirstVisitLocationPopover() {
        var locationPopover = this.page.getLocationPopoverOnFirstPageVisit();
        locationPopover.getYesBtn().click();
        return this;
    }
}
