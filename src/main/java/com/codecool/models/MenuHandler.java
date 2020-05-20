package com.codecool.models;

import com.codecool.utilities.*;

import static com.codecool.models.UserTypes.*;

public class MenuHandler {
    public boolean isRunning;
    private final String[] menu = {"Welcome to CcMS" ,"1. Log in", "0. Exit"};


    public MenuHandler() {
        isRunning = true;
    }

    public void mainMenu() {
        View.getInstance().showMenu(menu);
        switch (InputProvider.getInstance().getInt("SELECT OPTION: ")){
            case 1:
                login();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                System.out.println("jeszcze raz");
        }
    }

    private void login() {
        String email;
        do {
            email = InputProvider.getString("Enter Email: ");
            }while (!EmailValidation.isValidEmail(email));
        String password = InputProvider.getString("Enter Password: ");
        View.getInstance().clearScreen();
        UserTypes userType = (accountValidation()) ? selectTypeOfAccount() : NONE;
        switch (userType){
            case MENTOR:
                MentorMenu.getInstance().menu();
                break;
            case ADMIN:
                AdminMenu.getInstance().menu();
                break;
            case OFFICE_MEMBER:
                OfficeMemberMenu.getInstance().menu();
                break;
            case STUDENT:
                StudentMenu.getInstance().menu();
                break;
            case NONE:
                View.getInstance().wrongData();
        }
    }

    private UserTypes selectTypeOfAccount() {
        return OFFICE_MEMBER;
    }

    private boolean accountValidation() {
        return true;
    }


}
