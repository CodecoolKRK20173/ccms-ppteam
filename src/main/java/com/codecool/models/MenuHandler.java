package com.codecool.models;

import com.codecool.utilities.*;

import java.util.HashMap;

public class MenuHandler {
    public boolean isRunning;
    private View view;
    private InputProvider inputProvider;

    public MenuHandler() {
        view = new View();
        inputProvider = new InputProvider();
        isRunning = true;
    }

    public void mainMenu() {
        view.mainMenu();
        switch (inputProvider.getInt("SELECT OPTION: ")){
            view.clearScreen();
            case 1:
                login();
                break;
            case 2:
                isRunning = false;
                break;
        }
    }

    private void login() {
        String email = InputProvider.getString("Enter Email: ");
        String password = InputProvider.getString("Enter Password: ");
        view.clearScreen();
        accountValidation();
        switch (InputProvider.getInt("choice: ")){
            case 1:
                new AdminMenu.getInstance();
                break;
            case 2:
                new mentorMenu();
                break;
            case 3:
                new officeMemberMenu();
                break;
            case 4:
                new studentMenu();
                break;
            default:
                view.wrongData();
        }
    }

    private void accountValidation() {

    }


}
