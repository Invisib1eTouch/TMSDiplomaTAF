package models;

import lombok.Data;

@Data
public class CartItemModel {
    private final String name;
    private final String description;
    private final String price;
}
