package pages;

import com.codeborne.selenide.SelenideElement;
import models.containers.ProductContainer;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage extends CommonHeader {

    private static final By productTitleBy = By.className("catalog-masthead__title");

    public ProductDetailsPage() {
        super(UrlPrefix.DEFAULT, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return productTitleBy;
    }

    public ProductContainer getProductDetails() {
        return new ProductContainer(
                $(productTitleBy),
                $(".offers-description__specs p"),
                $(".offers-description__price a"));
    }
}
