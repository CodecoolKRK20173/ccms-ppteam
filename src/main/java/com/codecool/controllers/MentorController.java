package com.codecool.controllers;

import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;
import com.codecool.user.User;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

import java.util.List;

public class MentorController {
    private static MentorController instance;
    private String[] menu = {"1.Show students list", "2.Add Assignment", "3.Grade an assignment", "4.Check Attendance" ,
            "5.Add student to class", "6.Remove Student from class", "7.Edit student data", "0.Exit"};

    public static MentorController getInstance() {
        if (instance == null) {
            synchronized(MentorController.class) {
                if (instance == null) {
                    instance = new MentorController();
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
                    getStudentsList();//view
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    addStudentToClassroom();
                    break;
                case 6:
                    removeStudentFromClassroom();
                    break;
                case 7:
                    editStudentData();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    View.getInstance().wrongData();
            }

        }
    }

    private List<User> getStudentsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.STUDENT);
    }

    private void addStudentToClassroom() {
        int studentId = InputProvider.getInt("Enter student id: ");
        String newClassroom = InputProvider.getString("Enter new classroom name: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", newClassroom);
    }

    private void removeStudentFromClassroom() {
        int studentId = InputProvider.getInt("Enter student id: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", null);
    }

    private void editStudentData() {
        int studentId = InputProvider.getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getString("Enter column to edit: ");
        String newParamData = InputProvider.getString("Enter new data: ");
        UserDao.getInstance().editUserDataById(studentId, "UserDetails", columnToEdit, newParamData);
    }
}
