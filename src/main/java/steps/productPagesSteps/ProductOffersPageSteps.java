package steps.productPagesSteps;

import pages.productPages.ProductOffersPage;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    public ProductOffersPageSteps addLowerPriceOfferToCart() {
        this.page.getLowerPriceOffer().getAddToCartBtn().click();
        return this;
    }
}
