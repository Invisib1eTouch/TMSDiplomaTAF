package steps.productPagesSteps;

import pages.productPages.productOffersPage.ProductOffersPage;

public class ProductOffersPageSteps extends ProductSummaryPageSteps<ProductOffersPage> {

    public ProductOffersPageSteps addLowerPriceOfferToCart() {
        this.page.getLowerPriceOffer().getAddToCartBtn().click();
        return this;
    }
}
