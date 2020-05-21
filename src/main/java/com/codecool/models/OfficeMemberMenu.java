package com.codecool.models;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.user.OfficeMember;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class OfficeMemberMenu {
    private static OfficeMemberMenu instance;
    private OfficeMember officeMember;
    private final String[] menu = {"1.Show students list", "0.Exit"};
    UsersContainer usersContainer = new UsersContainer();

    private OfficeMemberMenu(){}

    public static OfficeMemberMenu getInstance() {
        if (instance == null) {
            synchronized(OfficeMemberMenu.class) {
                if (instance == null) {
                    instance = new OfficeMemberMenu();
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
