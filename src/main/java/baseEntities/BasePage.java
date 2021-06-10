package baseEntities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import core.PropertyReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public abstract class BasePage {

    private final String path;

    /**
     *
     * @param urlPrefix - prefix for the page e.g. catalog, profile, etc.
     * @param path - if page has no constant path then path = null (e.g. dialogue or dynamic path)
     */
    public BasePage(UrlPrefix urlPrefix, String path) {
        Configuration.baseUrl = String.format("https://%s.%s", urlPrefix.getValue(), PropertyReader.getBaseUrl());
        this.path = path;
    }

    /**
     * @return - by locator of the element-indicator that correct page is opened
     */
    protected abstract By getCorrectPageOpenedIndicatorElLocator();

    public void verifyCorrectPageOpened() {
        try {
            // Check that element that indicates particular page can be found and visible
            $(this.getCorrectPageOpenedIndicatorElLocator()).should(exist, be(visible));
        } catch (Error e) {
            throw new AssertionError(String.format("%s was not opened\n Detailed Message:\n%s",
                            this.getClass().getSimpleName(), e.getMessage()));
        }
    }

    public void open() {
        if (this.path != null)
            com.codeborne.selenide.Selenide.open(this.path);
    }

    public void openAndVerifyCorrectPageOpened() {
        this.open();
        this.verifyCorrectPageOpened();
    }

    @AllArgsConstructor
    protected enum UrlPrefix {
        CATALOG_PREFIX(PropertyReader.getUrlPrefix("catalog.url.prefix")),
        PROFILE_PREFIX(PropertyReader.getUrlPrefix("profile.url.prefix")),
        CART_PREFIX(PropertyReader.getUrlPrefix("cart.url.prefix"));

        @Getter
        private final String value;
    }


    public int getElementTextLength(SelenideElement selenideElement){
        return Objects.requireNonNull(selenideElement.getValue()).length();
    }

}
