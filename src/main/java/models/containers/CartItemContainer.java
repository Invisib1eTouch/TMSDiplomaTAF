package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import models.CartItemModel;
import wrappers.HiddenButton;

@Getter
public class CartItemContainer extends ProductContainer{
    private final HiddenButton deleteBtn;

    public CartItemContainer(SelenideElement name, SelenideElement description, SelenideElement price, HiddenButton deleteBtn) {
        super(name, description, price);
        this.deleteBtn = deleteBtn;
    }

    @Override
    public CartItemModel getCartItemModel(){
        CartItemModel cartItemModel = super.getCartItemModel();
        cartItemModel.setName(this.getTextName());
        return cartItemModel;
    }
}
