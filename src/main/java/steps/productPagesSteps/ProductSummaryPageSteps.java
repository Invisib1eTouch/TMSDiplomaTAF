package steps.productPagesSteps;

import pages.productPages.ProductSummaryPage;
import steps.commonSteps.CommonHeaderSteps;

public abstract class ProductSummaryPageSteps<Page extends ProductSummaryPage> extends CommonHeaderSteps<Page> {

    protected ProductSummaryPageSteps() {
        super(false);
    }

    public ProductOffersPageSteps openProductOffersPageThroughPrice() {
        this.page.getProductSummary().getPrice().click();
        return new ProductOffersPageSteps();
    }
}
