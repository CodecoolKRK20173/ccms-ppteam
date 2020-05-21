package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.user.OfficeMember;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class OfficeMemberController {
    private static OfficeMemberController instance;
    private OfficeMember officeMember;
    private final String[] menu = {"1.Show students list", "0.Exit"};
    UsersContainer usersContainer = new UsersContainer();

    private OfficeMemberController(){}

    public static OfficeMemberController getInstance() {
        if (instance == null) {
            synchronized(OfficeMemberController.class) {
                if (instance == null) {
                    instance = new OfficeMemberController();
                }
            }
        }
        return instance;
    }

    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInt("SELECT OPTION: ")){
                case 1:
//                    View.getInstance().printTable();
                    System.out.println(usersContainer.getStudentsList());
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    View.getInstance().wrongData();
        }

        }
    }
}
