package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.user.User;
import com.codecool.utilities.*;

import static com.codecool.models.UserTypes.*;

public class MenuController {
    public boolean isRunning;
    private final String[] menuPrompt = {"Welcome to CcMS" ,"1. Log in", "0. Exit"};

    public MenuController() {
        isRunning = true;
        UserDao userDao = new UserDao();
        userDao.initializeUsers();
    }

    public void mainMenu() {
        View.getInstance().clearScreen();
        View.getInstance().showMenu(menuPrompt);
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
        User user = getUser(getUserData());
        switch ((user == null ? NONE : user.getType())){
            case MENTOR:
                new MentorController(user).menu();
                break;
            case ADMIN:
                new AdminController().menu();
                break;
            case OFFICE_MEMBER:
                new OfficeMemberController().menu();
                break;
            case STUDENT:
                new StudentController(user).menu();
                break;
            case NONE:
                View.getInstance().wrongData();
        }
    }

    private String[] getUserData() {
        return InputProvider.getInstance().dataProvider();
    }

    private User getUser(String[] data) {
        return UsersContainer.getInstance().getUserByEmailAndPassword(data[0], data[1]);
    }

}
