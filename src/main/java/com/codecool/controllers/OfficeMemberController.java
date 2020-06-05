package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.models.UserTypes;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class OfficeMemberController {
    private final String[] menu = {"1.Show students list", "0.Exit"};

    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInstance().getInt("SELECT OPTION: ")){
                case 1:
                    View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT));
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
