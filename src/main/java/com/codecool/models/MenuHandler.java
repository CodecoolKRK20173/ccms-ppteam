package com.codecool.models;

import com.codecool.dao.UserDao;
import com.codecool.user.User;
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
        String email = InputProvider.getEmail();
        String password = InputProvider.getString("Enter Password: ");
        UserDao dao = new UserDao();
        UserTypes type = NONE;
        try {
            User user = dao.getUserByEmailandPassword(email, password);
            type = user.getType();
        }catch (Exception e){}
        View.getInstance().clearScreen();
        switch (type){
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

}
