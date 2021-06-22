package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import models.containers.CartItemContainer;
import pages.CartPage;
import services.SQLRequestSender;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.exist;

@Slf4j
public class CartPageSteps extends BaseStep<CartPage> {
    /**
     * @param openPageByUrl - if true page will be opened by url
     * @implNote - if page can not be opened by url, false value hardcoded to super constructor e.g. public
     * ProfileMenuSteps() { super(false); }
     */
    public CartPageSteps(Boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Step("Get cart item by product name")
    public CartItemContainer getCartItemByName(String productName) {
        return this.page.getCartItems()
                .stream()
                .filter(el -> el.getTextName().equals(productName))
                .reduce((el1, el2) -> {
                    var errMes = "More than one item found with provided name: " + productName;
                    log.error(errMes);
                    throw new IllegalStateException(errMes);
                })
                .orElseThrow(() -> {
                    var errMes = "No such item in the cart with provided name: " + productName;
                    log.error(errMes);
                    return new NoSuchElementException(errMes);
                });
    }

    @Step("Verify cart item exists")
    public boolean cartItemExist(String productName) {
        if(!this.page.getTotalProductsAddedLabel().exists()) return false;

        try {
            this.getCartItemByName(productName);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    @Step("Delete cart item by product name")
    public CartPageSteps deleteItemFromCartByName(String productName){
        this.getCartItemByName(productName).getDeleteBtn().click();
        this.page.getCartPageLoader().shouldNot(exist);
        return this;
    }
}