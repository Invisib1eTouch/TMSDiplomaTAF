package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

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
        return this.name.getValue();
    }
}