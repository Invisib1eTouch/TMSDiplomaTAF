package wrappers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class HiddenButton {

    private final SelenideElement element;

    public HiddenButton(By btnLocator) {
        this.element = $(btnLocator);
    }

    public HiddenButton(SelenideElement element) {
        this.element = element;
    }

    public void click() {
        this.element.hover().should(appear).click();
    }
}
