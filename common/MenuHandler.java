package common;

public class MenuHandler {
    public static void displayMainMenu() {
        System.out.println("--------------------------------Welcome to our online store--------------------------------\n");
        System.out.println("1- Sign in as admin");
        System.out.println("2- Sign in as user");
        System.out.println("3- Create an account");
        System.out.println("4- Exit\n");
    }

    public static void displayAdminMenu() {
        System.out.println("\n1. View products");
        System.out.println("2. Add a product");
        System.out.println("3. Remove a product");
        System.out.println("4. Update a product");
        System.out.println("5. Create an admin account");
        System.out.println("6. Create a customer account");
        System.out.println("7. Remove a customer account");
        System.out.println("8. Exit\n");
    }

    public static void displayCustomerMenu() {
        System.out.println("\n1- Browse Products");
        System.out.println("2- View items in shopping cart");
        System.out.println("3- Checkout shopping cart");
        System.out.println("4- Exit\n");
    }
}
