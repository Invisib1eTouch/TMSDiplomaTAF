package steps.productPagesSteps;

import io.qameta.allure.Step;
import pages.productPages.productOffersPage.ProductOffersPage;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    @Step("Adding offer with the lowest price to cart by clicking Add button.")
    public ProductOffersPageSteps addLowerPriceOfferToCart() {
        this.page.getLowerPriceOffer().getAddToCartBtn().click();
        return this;
    }

    @Step("Applying location popup that is displayed on first site visiting with provided value by default.")
    public ProductOffersPageSteps handleFirstVisitLocationPopover() {
        var locationPopover = this.page.getLocationPopoverOnFirstPageVisit();
        locationPopover.getYesBtn().click();
        return this;
    }
}
