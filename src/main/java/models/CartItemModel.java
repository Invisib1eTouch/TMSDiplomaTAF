package models;

import dataObjects.json.products.ProductJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import utils.Utils;

import static utils.Utils.getMatchedText;

@Data
@AllArgsConstructor
public class CartItemModel {
    private String name;
    private String description;
    private String price;

    public CartItemModel(ProductJson productJson) {
        this.name = productJson.getFullName();
        this.description = productJson.getDescription().replace("&quot;", "\"");
        this.price = productJson.getPrices().getPriceMin().getAmount().replace('.', ',');
    }
}