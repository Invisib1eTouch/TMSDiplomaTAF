package services;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import models.CartItemModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLRequestSender {
    private static String query;

    @Step("[DB] Add Product to shopping_cart table")
    public static void addProductToShoppingCartTable(CartItemModel cartItemModel){
        query = String.format("INSERT INTO shopping_cart (product_name, product_description, product_price)" +
                "VALUES ('%s', '%s', '%s')", cartItemModel.getName(), cartItemModel.getDescription(), cartItemModel.getPrice());

        DBExecutor.execute(query);
    }

    @SneakyThrows
    @Step("[DB] Get Cart Item by Product name")
    public static List<CartItemModel> getCartItemByProductName(String productName){
        query = String.format("SELECT * FROM shopping_cart WHERE product_name = '%s'", productName);

        ResultSet results = DBExecutor.executeQuery(query);

        List<CartItemModel> cartItems = new ArrayList<>();
        while (results.next()){
            CartItemModel cartItem = new CartItemModel(
                    results.getString("product_name"),
                    results.getString("product_description"),
                    results.getString("product_price"));
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    @Step("[DB] Delete Cart Item by Product name")
    public static void deleteCartItemByProductName(String productName){
        query = String.format("DELETE FROM shopping_cart WHERE product_name = '%s'", productName);

        DBExecutor.execute(query);
    }
}