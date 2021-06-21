package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import enums.UrlPrefix;
import models.containers.CartItemContainer;
import org.openqa.selenium.By;
import wrappers.HiddenButton;

import java.util.List;
import java.util.stream.Collectors;

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
    private static final By cartPageLoaderBy = By.className("cart-form_animated");
    private static final By cartDeletedItemProductDescriptionBy = By.className("cart-form__description_condensed-extra");
    private static final By cartDeletedItemProductContainerBy = By.xpath("//a[contains(@class , 'cart-form__link_small')]//ancestor::div[contains(@class, 'cart-form__offers-unit')]");

    public CartPage() {
        super(UrlPrefix.CART, "");
        this.pageOpenIndConditionToFulfill = PageOpenIndConditionToFulfill.NOT_EXIST;
    }

    @Override
    protected By getCorrectPageOpenedIndicatorElLocator() {
        return cartPageLoaderBy;
    }

    public SelenideElement getCartPageLoader(){
        return $(cartPageLoaderBy);
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

    public int getDeletedCartItemsNumber() {
        return $$(cartDeletedItemProductContainerBy).size();
    }
}