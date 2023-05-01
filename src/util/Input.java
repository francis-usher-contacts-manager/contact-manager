package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        return scanner.next();
    }


    public int getInt() {
        String input = scanner.next();
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return getInt();
        }
    }

    public int getIntChoice() {
        return scanner.nextInt();
    }

    public double getDouble() {
        String input = scanner.next();
        try {
            return Double.valueOf(input);
        } catch(NumberFormatException e) {
            System.out.println("Invalid input. Please enter a double.");
            return getDouble();
        }
    }

    public boolean yesNo() {
        System.out.println("\nContinue? [Y/N]");
        String input = scanner.next();
        if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public int getInt(int min, int max) {
        int input;
        do {
            System.out.printf("Enter a number within the range of %s and %s%n", min, max);
            input = scanner.nextInt();

            if(input < min || input > max) {
                System.out.println("Invalid Input!");
            }
        } while(input < min || input > max);
        return input;
    }

    public double getDouble(double min, double max) {
        double input;
        do {
            System.out.printf("Enter a number within the range of %s and %s%n", min, max);
            input = scanner.nextDouble();

            if(input < min || input > max) {
                System.out.println("Invalid Input!");
            }
        } while(input < min || input > max);
        return input;
    }

}
