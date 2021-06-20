package steps.productPagesSteps;

import io.qameta.allure.Step;
import pages.productPages.ProductSummaryPage;
import services.SQLRequestSender;
import steps.commonSteps.CommonHeaderSteps;

public abstract class ProductSummaryPageSteps<Page extends ProductSummaryPage> extends CommonHeaderSteps<Page> {

    protected ProductSummaryPageSteps() {
        super(false);
    }

    @Step("Open Product Offers page by clicking product price.\"")
    public ProductOffersPageSteps openProductOffersPageThroughPrice() {
        var cartItemModel = this.page.getProductSummary().getCartItemModel();

        // DB crutch
        SQLRequestSender.addProductToCartTable(cartItemModel);
        this.page.getProductSummary().getPrice().click();
        // DB crutch

        return new ProductOffersPageSteps();
    }
}
