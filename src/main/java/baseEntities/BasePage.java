package baseEntities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.PropertyReader;
import enums.UrlPrefix;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public abstract class BasePage {

    private final String path;
    protected PageOpenIndConditionToFulfill pageOpenIndConditionToFulfill = PageOpenIndConditionToFulfill.EXIST_AND_VISIBLE;

    /**
     * @param urlPrefix - prefix for the page e.g. catalog, profile, etc., if path = null, use - DEFAULT
     * @param path      - if page has no constant path then path = null (e.g. dialogue or dynamic path)
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
            // Check that page opened indicator element fulfills provided conditions
            $(this.getCorrectPageOpenedIndicatorElLocator()).should(this.pageOpenIndConditionToFulfill.condition, Duration.ofSeconds(10));
        } catch (Error e) {
            var errMes = String.format("%s was not opened\n Detailed Message:\n%s",
                    this.getClass().getSimpleName(), e.getMessage());
            log.error(errMes);
            throw new AssertionError(errMes);
        }
    }

    @SneakyThrows
    public void open() {
        if (this.path != null)
            try {
                com.codeborne.selenide.Selenide.open(this.path);
            } catch (Exception e){
                var errMes = String.format("Couldn't open page with by provided url: '%s' \nDetailed message: \n%s",
                        WebDriverRunner.url(), e.getMessage());
                log.error(errMes);
                throw new Exception(errMes);
            }
    }

    /**
     * @param locatorParts - parts of css locator
     * @return - concatenated by space css locator
     */
    protected String getConcatenatedBySpaceCssLocator(String... locatorParts) {
        StringBuilder resultingLocator = new StringBuilder(locatorParts[0]);

        for (int i = 1; i < locatorParts.length; i++) {
            resultingLocator.append(" ").append(locatorParts[i]);
        }
        return resultingLocator.toString();
    }

    protected enum PageOpenIndConditionToFulfill {
        EXIST_AND_VISIBLE(and("", exist, be(visible))),
        NOT_EXIST(not(exist));

        @Getter
        private final Condition condition;

        PageOpenIndConditionToFulfill(Condition condition) {
            this.condition = condition;
        }
    }
}