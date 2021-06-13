package steps;

import baseEntities.BaseStep;
import pages.SearchResultsFrame;
import steps.productPagesSteps.ProductDetailsPageSteps;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;

public class SearchResultsFrameSteps extends BaseStep<SearchResultsFrame> {

    public SearchResultsFrameSteps() {
        super(false);
    }

    public SearchResultsFrameSteps waitLoadingFinished() {
        this.page.getSearchLoader().shouldNot(exist, Duration.ofSeconds(10));
        return this;
    }

    public ProductDetailsPageSteps openProductDetailsPageByName(String productName) {
        this.page.getSearchResultItemByName(productName).getTitle().click();
        return new ProductDetailsPageSteps();
    }

}
