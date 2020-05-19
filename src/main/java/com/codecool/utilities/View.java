package com.codecool.utilities;

public class View {
    private static View instance;

    public static View getInstance() {
        if (instance == null) {
            synchronized(View.class) {
                if (instance == null) {
                    instance = new View();
                }
            }
        }
        return instance;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void wrongData() {
        System.out.println("Invalid Data.");
    }

    public void showMenu(String[] strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
