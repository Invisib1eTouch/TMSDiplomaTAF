package steps.productPagesSteps;

import io.qameta.allure.Step;
import models.CartItemModel;
import pages.productPages.ProductSummaryPage;
import services.SQLRequestSender;
import steps.commonSteps.CommonHeaderSteps;

public abstract class ProductSummaryPageSteps<Page extends ProductSummaryPage> extends CommonHeaderSteps<Page> {

    protected ProductSummaryPageSteps() {
        super(false);
    }

    @Step("Open Product Offers page")
    public ProductOffersPageSteps openProductOffersPageThroughPrice() {
        this.page.getProductSummary().getPrice().click();
        return new ProductOffersPageSteps();
    }
}