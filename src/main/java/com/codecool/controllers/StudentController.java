package com.codecool.controllers;

import com.codecool.user.Student;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class StudentController {
    private static StudentController instance;
    private Student student;
    private String[] menu = {"1.Show grades" , "2.Submit assignment", "0. Exit"};

    public static StudentController getInstance() {
        if (instance == null) {
            synchronized(MentorController.class) {
                if (instance == null) {
                    instance = new StudentController();
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