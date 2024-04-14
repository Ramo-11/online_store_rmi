package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import common.Product;

@SuppressWarnings("unused")
public class DataController {
    public static List<User> downloadUsers(String type) {
        List<User> users = new ArrayList<User>();
        if (!type.equals("admin") && !type.equals("customer")) {
            System.out.println("Invalid user type, must be 'admin' or 'customer'");
            return null;
        }
        String fileName;
        if (type.equals("admin")) {
            fileName = "Admins.txt";
        } else {
            fileName = "Customers.txt";
        }
        String name;
        String accountPin;

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            
            while (input.hasNextLine()) {
                name = input.nextLine();
                accountPin = input.nextLine();
                users.add(new User(name, accountPin, type));
            }
            input.close();
            return users;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    public static void uploadUsers(List<User> admins, String fileName) {
        try {
            FileWriter outFile = new FileWriter(fileName, false);
            PrintWriter output = new PrintWriter(outFile);
                    
            for (int i = 0; i < admins.size(); i++) {
                if(i != 0) {
                    output.print("\n");
                }
                output.print(admins.get(i).getName());
                output.print("\n");
                output.print(admins.get(i).getAccountPin());
            }
            outFile.close();
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Products downloadProducts() {
        Products products = new Products();
        double price; 
        String name;
        String description;
        int quantity;
        String endLine;

        try {
            File theFile = new File("Products.txt");
            Scanner input = new Scanner(theFile);
            
            while (input.hasNextLine()) {
                name = input.nextLine();
                if(!input.hasNextDouble()) {
                    break;
                }
                price = input.nextDouble();
                endLine = input.nextLine();
                description = input.nextLine();
                if(!input.hasNextInt()) {
                    break;
                }
                quantity = input.nextInt();
                if(input.hasNextLine()) {
                    endLine = input.nextLine();
                }
                products.addProduct(new Product(name, price, description, quantity));
            }
            input.close();
            return products;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    public static void uploadProducts(Products products) {
        try {
            FileWriter outFile = new FileWriter("Products.txt", false);
            PrintWriter output = new PrintWriter(outFile);
                    
            for (int i = 0; i < products.size(); i++) {
                output.println(products.get(i).getName());
                output.println(products.get(i).getPrice());
                output.println(products.get(i).getDescription());
                output.println(products.get(i).getQuantity());
            }
            outFile.close();
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
