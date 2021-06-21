package steps.productPagesSteps;

import io.qameta.allure.Step;
import pages.productPages.ProductDetailsPage;

public class ProductDetailsPageSteps extends ProductSummaryPageSteps<ProductDetailsPage> {

    @Step("Open Product Details page by ULR.")
    public static ProductDetailsPageSteps openPage(String productUrl){
        com.codeborne.selenide.Selenide.open(productUrl);
        return new ProductDetailsPageSteps();
    }
}
