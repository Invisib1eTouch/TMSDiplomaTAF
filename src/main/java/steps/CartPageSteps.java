package steps;

import baseEntities.BaseStep;
import models.containers.CartItemContainer;
import pages.CartPage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CartPageSteps extends BaseStep<CartPage> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     * @implNote - if page can not be opened by url, false value hardcoded to super constructor e.g. public
     * ProfileMenuSteps() { super(false); }
     */
    public CartPageSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }

    public CartItemContainer getCartItemByName(String productName) {
        return this.page.getCartItems()
                .stream()
                .filter(el -> el.getTextName().equals(productName))
                .reduce((el1, el2) -> {
                    throw new IllegalStateException("More than one item found with provided name: " + productName);
                })
                .orElseThrow(() -> new NoSuchElementException("No such item in the cart with provided name: " + productName));
    }

    public boolean cartItemExist(String productName) {
        try {
            this.getCartItemByName(productName);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
}
