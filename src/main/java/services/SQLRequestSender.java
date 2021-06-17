package services;

import models.CartItemModel;

public class SQLRequestSender {

    public static void addProductToCartTable(CartItemModel cartItemModel){
        var query = String.format("INSERT INTO shopping_cart (product_name, product_description, product_price)" +
                "VALUES ('%s', '%s', '%s')", cartItemModel.getName(), cartItemModel.getDescription(), cartItemModel.getPrice());

        DBExecutor.execute(query);
    }
}
