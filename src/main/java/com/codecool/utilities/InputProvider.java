package com.codecool.utilities;

import java.util.Scanner;

public class InputProvider {
    private static final Scanner scanner = new Scanner(System.in);
    private static InputProvider instance;

    public static InputProvider getInstance() {
        if (instance == null) {
            synchronized(InputProvider.class) {
                if (instance == null) {
                    instance = new InputProvider();
                }
            }
        }
        return instance;
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Input is not a number.");
            scanner.nextLine();
        }
        View.getInstance().clearScreen();
        return scanner.nextInt();
    }


    public String getString(String prompt) {
        System.out.print(prompt);
        String text = scanner.next();
        View.getInstance().clearScreen();
        return text;
    }

    public String getEmail() {
        String email;
        do {
            email = getString("Enter Email: ");
        }while (!EmailValidation.isValidEmail(email));
        View.getInstance().clearScreen();
        return email;
    }

    public String[] dataProvider() {
        return new String[]{getEmail(), getString("Enter Password: ")};
    }
}

