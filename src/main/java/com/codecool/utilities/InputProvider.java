package com.codecool.utilities;

import java.util.Scanner;

public class InputProvider {
        private static Scanner scanner = new Scanner(System.in);

        public static int getInt(String prompt) {
            System.out.print(prompt);
            return scanner.nextInt();
        }

        public static String getString(String prompt) {
            System.out.print(prompt);
            return scanner.next();
        }
    }

