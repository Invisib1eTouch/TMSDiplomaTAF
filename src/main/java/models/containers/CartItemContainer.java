package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import wrappers.HiddenButton;

@Getter
public class CartItemContainer extends ProductContainer{
    private final HiddenButton deleteBtn;

    public CartItemContainer(SelenideElement title, SelenideElement description, SelenideElement price, HiddenButton deleteBtn) {
        super(title, description, price);
        this.deleteBtn = deleteBtn;
    }
}
