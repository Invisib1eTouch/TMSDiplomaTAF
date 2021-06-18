package steps.productPagesSteps;

import pages.productPages.ProductSummaryPage;
import services.SQLRequestSender;
import steps.commonSteps.CommonHeaderSteps;

public abstract class ProductSummaryPageSteps<Page extends ProductSummaryPage> extends CommonHeaderSteps<Page> {

    protected ProductSummaryPageSteps() {
        super(false);
    }

    public ProductOffersPageSteps openProductOffersPageThroughPrice() {
//        var cartItemModel = this.page.getProductSummary().getCartItemModel();
//        SQLRequestSender.addProductToCartTable(cartItemModel);
        this.page.getProductSummary().getPrice().click();
        return new ProductOffersPageSteps();
    }
}
