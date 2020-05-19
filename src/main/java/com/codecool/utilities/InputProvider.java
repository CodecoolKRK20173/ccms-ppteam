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
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}

