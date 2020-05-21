package com.codecool.utilities;

import java.util.Scanner;

public class InputProvider {
    private static Scanner scanner = new Scanner(System.in);
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

    public static int getInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Input is not a number.");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }


    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public static String getEmail() {
        String email;
        do {
            email = InputProvider.getString("Enter Email: ");
        }while (!EmailValidation.isValidEmail(email));
        return email;
    }

    public static String[] dataProvider() {
        return new String[]{getEmail(), getString("Enter Password: ")};
    }
}

