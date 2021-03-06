package com.codecool.controllers;

import com.codecool.dao.AssignmentDao;
import com.codecool.models.Assignment;
import com.codecool.user.Student;
import com.codecool.user.User;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class StudentController {
    private final Student student;
    private final String[] menu = {"1.Show assignments" , "2.Submit assignment","3.Check your attendance", "0. Exit"};

    StudentController(User student){
        this.student = (Student) student;
    }


    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInstance().getInt("SELECT OPTION: ")){
                case 1:
                    View.getInstance().showAssignmentTable(student);
                    break;
                case 2:

                    break;
                case 3:
                    View.getInstance().showAttendanceTable(this.student.getId());
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
