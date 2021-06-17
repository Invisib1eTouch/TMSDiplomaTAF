package steps.productPagesSteps;

import pages.productPages.productOffersPage.ProductOffersPage;
import services.SQLRequestSender;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    public ProductOffersPageSteps addLowerPriceOfferToCart() {
        this.page.getLowerPriceOffer().getAddToCartBtn().click();
        var cartItemModel = this.page.getProductSummary().getCartItemModel();
        SQLRequestSender.addProductToCartTable(cartItemModel);
        return this;
    }

    public ProductOffersPageSteps handleFirstVisitLocationPopover() {
        var locationPopover = this.page.getLocationPopoverOnFirstPageVisit();
        locationPopover.getYesBtn().click();
        return this;
    }
}
