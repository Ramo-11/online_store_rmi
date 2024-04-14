package common;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Helper {
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clearScreen() { 
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    @SuppressWarnings("resource")
    public static void getCredentials(User user) {
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter name: ");
        user.setName(scan.nextLine());
        
        System.out.print("Enter pin: ");
        user.setAccountPin(scan.nextLine());
    }

    public static void clearCredentials(User user) {
        user.setName("Default Name");
        user.setAccountPin("12345");
    }

    @SuppressWarnings("resource")
    public static int getUserNumInput() {
        Scanner helperScanner = new Scanner(System.in);
        int userChoice = 0;
        boolean valid = false;
    
        while (!valid) {
            System.out.println("Please enter a number:");
            if (helperScanner.hasNextInt()) {
                userChoice = helperScanner.nextInt();
                valid = true;
            } else {
                System.out.println("Input is invalid, please enter a valid number.");
                helperScanner.next();
            }
        }
        return userChoice;
    }
}
