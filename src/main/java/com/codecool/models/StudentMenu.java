package com.codecool.models;

import com.codecool.user.Student;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class StudentMenu {
    private static StudentMenu instance;
    private Student student;
    private String[] menu = {"1.Show grades" , "2.Submit assignment", "0. Exit"};

    public static StudentMenu getInstance() {
        if (instance == null) {
            synchronized(MentorMenu.class) {
                if (instance == null) {
                    instance = new StudentMenu();
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
                case 0:
                    isRunning = false;
                    break;
                default:
                    View.getInstance().wrongData();
            }

        }
    }


}
