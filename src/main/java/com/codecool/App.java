package com.codecool;

import com.codecool.models.MenuHandler;

public class App
{
    public static void main( String[] args )
    {
        MenuHandler menuHandler = new MenuHandler();
        while (menuHandler.isRunning) {
            menuHandler.mainMenu();
        }
    }
}
