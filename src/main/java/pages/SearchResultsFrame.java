package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import models.containers.ProductContainer;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SearchResultsFrame extends BasePage {

    private static final By searchInputBy = By.className("search__input");
    private static final By searchLoaderBy = By.className("search__bar_searching");

    public SearchResultsFrame() {
        super(UrlPrefix.DEFAULT, null);
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return searchInputBy;
    }

    public SelenideElement getSearchLoader() {
        return $(searchLoaderBy);
    }

    public ProductContainer getSearchResultItemByName(String productName) {
        SelenideElement container = $(byXpath(String.format("//a[contains(text(), '%s')]/ancestor::li", productName)));
        return new ProductContainer(
                container.$(byClassName("product__title-link")),
                container.$(byClassName("product__description")),
                container.$(".product__price-value span"));
    }
}
