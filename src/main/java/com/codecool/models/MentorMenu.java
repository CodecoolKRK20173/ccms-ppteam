package com.codecool.models;

import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class MentorMenu {
    private static MentorMenu instance;
    private String[] menu = {"1.Show students list", "2.Add Assignment", "3.Grade an assignment", "4.Check Attendance" ,
            "5.Add student to class", "6.Remove Student from class", "7.Edit student data", "0.Exit"};

    public static MentorMenu getInstance() {
        if (instance == null) {
            synchronized(MentorMenu.class) {
                if (instance == null) {
                    instance = new MentorMenu();
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
                case 6:
                    break;
                case 7:
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
