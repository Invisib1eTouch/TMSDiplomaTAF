package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemModel {
    private String name;
    private String description;
    private String price;
}
