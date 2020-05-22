package com.codecool;

import com.codecool.controllers.MenuController;

public class App
{
    public static void main( String[] args )
    {

        MenuController menuController = new MenuController();
        while (menuController.isRunning) {
            menuController.mainMenu();
        }
    }
}
