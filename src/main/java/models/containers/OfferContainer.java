package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static utils.Utils.getMatchedText;

@Data
@Slf4j
public class OfferContainer {
    private final SelenideElement price;
    private final SelenideElement addToCartBtn;

    public float getFloatPrice() {
        return Float.parseFloat(getMatchedText(this.price.getOwnText(), "(?<=.*)\\d*,\\d{2}(?=.*)")
                .replace(',', '.'));
    }
}