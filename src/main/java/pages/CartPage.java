package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.SoftAssertionError;
import com.codeborne.selenide.testng.SoftAsserts;
import models.containers.CartItemContainer;
import org.openqa.selenium.By;
import wrappers.HiddenButton;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage extends BasePage {
    private static final By titleBy = By.className("cart-form__title");
    private static final By totalProductsAddedLabelBy = By.className("cart-form__description_extended");
    private static final By cartItemContainerBy = By.className("cart-form__offers-unit");
    private static final By cartItemProductNameBy = By.className("cart-form__link_base-alter");
    private static final By cartItemProductDescriptionBy = By.cssSelector(".cart-form__description_primary.helpers_hide_tablet");
    private static final By cartItemProductPriceBy = By.cssSelector(".cart-form__offers-part_action span");
    private static final By cartItemDeleteBtnBy = By.className("cart-form__button_remove");

    public CartPage() {
        super(UrlPrefix.CART, "");
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return titleBy;
    }

    public List<CartItemContainer> getCartItems() {
        return $$(cartItemContainerBy).shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream()
                .map(cartItemEl -> new CartItemContainer(
                        cartItemEl.$(cartItemProductNameBy),
                        cartItemEl.$(cartItemProductDescriptionBy),
                        cartItemEl.$(cartItemProductPriceBy),
                        new HiddenButton(cartItemEl.$(cartItemDeleteBtnBy))))
                .collect(Collectors.toList());
    }

    public SelenideElement getTotalProductsAddedLabel(){
        return $(totalProductsAddedLabelBy);
    }

    public int getCartItemsNumber() {
        if (!this.getTotalProductsAddedLabel().exists())
            return 0;
        return $$(cartItemContainerBy).shouldHave(CollectionCondition.sizeGreaterThan(0)).size();
    }
}