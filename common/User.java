package common;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String accountPin;
    private String type;
    private Products products;
    private Products shoppingCart;

    public User() {
        this.name = "Default Name";
        this.accountPin = "12345";
        this.type = "customer";
        this.products = new Products();
        this.shoppingCart = new Products();
    }

    public User(String name, String accountPin, String type) {
        this.name = name;
        this.accountPin = accountPin;
        this.type = type;
        this.products = new Products();
        this.shoppingCart = new Products();
    }

    public User(String name, String accountPin, String type, Products products) {
        this.name = name;
        this.accountPin = accountPin;
        this.type = type;   
        this.products = products;
        this.shoppingCart = new Products();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public String getName() {
        return this.name;
    }

    public String getAccountPin() {
        return this.accountPin;
    }

    public String getType() {
        return this.type;
    }   

    public Products getProducts() {
        return this.products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getShoppingCart() {
        return this.shoppingCart;
    }

    public void addItemToShoppingCart(Product product) {
        this.shoppingCart.addProduct(product);
    }
}
