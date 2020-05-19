package com.codecool.models;

import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class AdminMenu {
    private static AdminMenu instance;
    private String[] menu = {"1.Show students list", "2.Show mentors list" , "3.Add new mentor", "4.Remove mentor", "5.Edit mentors data", "0.Exit"};

    public static AdminMenu getInstance() {
        if (instance == null) {
            synchronized(MentorMenu.class) {
                if (instance == null) {
                    instance = new AdminMenu();
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
