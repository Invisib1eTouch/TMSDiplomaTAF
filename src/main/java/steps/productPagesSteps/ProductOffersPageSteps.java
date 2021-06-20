package steps.productPagesSteps;

import io.qameta.allure.Step;
import pages.productPages.productOffersPage.ProductOffersPage;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    @Step("Adding offer with the lowest price to the cart by clicking 'To Cart' button.")
    public ProductOffersPageSteps addLowerPriceOfferToCart() {
        this.page.getLowerPriceOffer().getAddToCartBtn().click();
        return this;
    }

    @Step("Location confirmation popup that is displayed on the first site visiting with provided by default location value.")
    public ProductOffersPageSteps handleFirstVisitLocationPopover() {
        var locationPopover = this.page.getLocationPopoverOnFirstPageVisit();
        locationPopover.getYesBtn().click();
        return this;
    }
}
