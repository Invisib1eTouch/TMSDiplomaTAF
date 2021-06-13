package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

@Data
public class OfferContainer {
    private final SelenideElement price;
    private final SelenideElement addToCartBtn;

    public float getFloatPrice(){
        return Float.parseFloat(this.price.getOwnText().split(" ")[0]);
    }
}
