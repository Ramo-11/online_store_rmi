package common;

import java.util.Scanner;

public class Helper {
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
