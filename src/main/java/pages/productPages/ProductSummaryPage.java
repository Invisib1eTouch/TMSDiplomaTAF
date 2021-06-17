package pages.productPages;

import enums.UrlPrefix;
import models.containers.ProductContainer;
import org.openqa.selenium.By;
import pages.CommonHeader;

import static com.codeborne.selenide.Selenide.$;

public abstract class ProductSummaryPage extends CommonHeader {

    private static final By productTitleBy = By.className("catalog-masthead__title");
    private static final By productDescriptionBy = By.cssSelector(".offers-description__specs p");
    private static final By productPriceBy = By.cssSelector(".offers-description__price");

    public ProductSummaryPage() {
        super(UrlPrefix.DEFAULT, null);
    }

    public ProductContainer getProductSummary() {
        return new ProductContainer(
                $(productTitleBy),
                $(productDescriptionBy),
                $(productPriceBy));
    }
}
