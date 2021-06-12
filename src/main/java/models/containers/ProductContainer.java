package models.containers;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

@Data
public class ProductContainer {
    private final SelenideElement title;
    private final SelenideElement description;
    private final SelenideElement price;
}