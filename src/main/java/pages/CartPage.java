package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private static final By titleBy = By.className("cart-form__title");

    public CartPage() {
        super(UrlPrefix.CART, "");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return titleBy;
    }
}
