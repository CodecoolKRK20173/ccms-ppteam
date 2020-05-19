package com.codecool.utilities;

public class View {

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void mainMenu() {
        System.out.println("Welcome to CcMS\n" +
                    "1. Login\n" +
                    "0. Exit");
    }

    public void wrongData() {
        System.out.println("Invalid Data.");
    }
}
