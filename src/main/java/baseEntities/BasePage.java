package baseEntities;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public abstract class BasePage {

    private final String path;

    /**
     *
     * @param path - if page has no constant path then path = null
     *             (e.g. dialogue or dynamic path)
     */
    public BasePage(String path) {
        this.path = path;
    }

    /**
     * @return - by locator of the element-indicator that correct page is opened
     */
    protected abstract By getCorrectPageOpenedIndicatorElLocator();

    public void verifyCorrectPageOpened() {
        try {
            $(this.getCorrectPageOpenedIndicatorElLocator()).shouldBe(visible);
        } catch (Error e) {
            throw new AssertionError("Requested page was not opened\n Detailed Message:\n" + e.getMessage());
        }
    }

    public void open() {
        if (this.path != null)
            com.codeborne.selenide.Selenide.open(this.path);
    }
}
