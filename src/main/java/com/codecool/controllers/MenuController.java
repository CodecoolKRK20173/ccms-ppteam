package com.codecool.controllers;

import com.codecool.dao.UserDao;
import com.codecool.models.*;
import com.codecool.user.User;
import com.codecool.utilities.*;

import static com.codecool.models.UserTypes.*;

public class MenuController {
    public boolean isRunning;
    private final String[] menu = {"Welcome to CcMS" ,"1. Log in", "0. Exit"};


    public MenuController() {
        isRunning = true;
//        UserDao.getInstance().initializeUsers();
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
        }
    }

    private void login() {
        String email = InputProvider.getEmail();
        String password = InputProvider.getString("Enter Password: ");
        UserDao dao = new UserDao();
        UserTypes type = NONE;
        try {
            User user = dao.getUserByEmailAndPassword(email, password);
            type = user.getType();
        }catch (Exception e){}
        View.getInstance().clearScreen();
        switch (type){
            case MENTOR:
                MentorController.getInstance().menu();
                break;
            case ADMIN:
                AdminController.getInstance().menu();
                break;
            case OFFICE_MEMBER:
                OfficeMemberController.getInstance().menu();
                break;
            case STUDENT:
                StudentController.getInstance().menu();
                break;
            case NONE:
                View.getInstance().wrongData();
        }
    }

}
