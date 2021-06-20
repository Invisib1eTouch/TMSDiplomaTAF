package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import pages.SearchResultsFrame;
import steps.productPagesSteps.ProductDetailsPageSteps;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;

public class SearchResultsFrameSteps extends BaseStep<SearchResultsFrame> {

    public SearchResultsFrameSteps() {
        super(false);
    }

    @Step("Wait until the search loader hides.")
    public SearchResultsFrameSteps waitLoadingFinished() {
        this.page.getSearchLoader().shouldNot(exist, Duration.ofSeconds(10));
        return this;
    }

    @Step("Open Product Details page by product name '{productName}'.")
    public ProductDetailsPageSteps openProductDetailsPageByName(String productName) {
        this.page.getSearchResultItemByName(productName).getName().click();
        return new ProductDetailsPageSteps();
    }

}
