package pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends CommonHeader {

    private static final By productTitleBy = By.className("catalog-masthead__title");

    public ProductDetailsPage() {
        super(null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return productTitleBy;
    }
}
