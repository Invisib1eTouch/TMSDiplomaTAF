package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import models.CartItemModel;

import static utils.Utils.getMatchedText;

@Getter
public class ProductContainer {
    private final SelenideElement name;
    private final SelenideElement description;
    private final SelenideElement price;

    public ProductContainer(SelenideElement name, SelenideElement description, SelenideElement price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getTextName() {
        return getMatchedText(this.name.getOwnText(), "(?<=\\s)[^\\s].+(?=\\s)");
    }

    public CartItemModel getCartItemModel(){
        return new CartItemModel(
                this.getFullNameFromExtended(),
                this.description.getOwnText(),
                getMatchedText(this.price.getText(), "(?<=.*)\\d*,\\d{2}(?=.*)"));
    }

    public String getFullNameFromExtended(){
        return getMatchedText(this.getTextName(), "(?<=.+ ).+");
    }
}