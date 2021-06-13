package pages.productPages;

import org.openqa.selenium.By;

public class ProductOffersPage extends ProductSummaryPage{

    private static final By offersListContainerBy = By.className("offers-list");

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return offersListContainerBy;
    }
}
