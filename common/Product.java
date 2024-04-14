package common;

import java.io.Serializable;

public class Product implements Serializable {
    String name;
    double price;
    String description;
    int quantity;

    public Product() {
        name = "Default";
        price = 0.0;
        description = "None";
        quantity = 0;
    }

    public Product(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(Product product) {
        this.name = product.name;
        this.price = product.price;
        this.description = product.description;
        this.quantity = product.quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
       return "name: " + getName() + "\nprice: " + getPrice() + "\ndescription: " + 
                getDescription() + "\nQuantity: " + getQuantity();
    }
}
