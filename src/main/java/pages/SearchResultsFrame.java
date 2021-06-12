package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

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
}
