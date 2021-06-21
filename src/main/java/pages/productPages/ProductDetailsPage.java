package pages.productPages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends ProductSummaryPage {

    private static final By productSpecsBy = By.id("specs");

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return productSpecsBy;
    }
}