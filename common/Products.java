package common;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Products implements Serializable {
    private List<Product> products;

    public Products() {
        products = new ArrayList<>();
    }
    public Products(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void displayProducts() {
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
            System.out.println();
        }
    }

    public void checkout() {
        System.out.println("Thank you for shopping with us!");
        products.clear();
    }

    public List<Product> getProducts() {
        return products;
    }

    public int size() {
        return products.size();
    }

    public Product get(int index) {
        return products.get(index);
    }
}
