package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
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
        UserDao.getInstance().initializeUsers();
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
        View.getInstance().clearScreen();
        switch (getUserType(InputProvider.dataProvider())){
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

    private UserTypes getUserType(String[] data) {
        UserTypes type = NONE;
        try {
            User user = UsersContainer.getInstance().getUserByEmailAndPassword(data[0], data[1]);
            type = user.getType();
        }catch (Exception e){}
        return type;

    }

}
