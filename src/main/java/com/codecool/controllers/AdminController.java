package com.codecool.controllers;

import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;
import com.codecool.user.User;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private static AdminController instance;
    private String[] menu = {"1.Show students list", "2.Show mentors list" , "3.Add new mentor", "4.Remove mentor", "5.Edit mentors data", "0.Exit"};

    public static AdminController getInstance() {
        if (instance == null) {
            synchronized(MentorController.class) {
                if (instance == null) {
                    instance = new AdminController();
                }
            }
        }
        return instance;
    }

    public void menu() throws SQLException {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInt("SELECT OPTION: ")){
                case 1:
                    getStudentsList();//view
                    break;
                case 2:
                    getMentorsList();//view
                    break;
                case 3:
                    addMentor();
                    break;
                case 4:
                    removeMentor();
                    break;
                case 5:
                    editMentorsData();
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

    private List<User> getMentorsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.MENTOR);
    }

    private void addMentor() throws SQLException {
        String name = InputProvider.getString("Enter name: ");
        String surname = InputProvider.getString("Enter surname: ");
        String email = InputProvider.getString("Enter email: ");
        String password = InputProvider.getString("Enter password");
        UserDao.getInstance().addMentor(name, surname, email, password);
    }

    private void removeMentor() throws SQLException {
        int mentorId = InputProvider.getInt("Enter mentors id: ");
        UserDao.getInstance().removeUserById(mentorId);
    }

    private void editMentorsData() {
        int mentorId = InputProvider.getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getString("Enter column to edit: ");
        String newParamData = InputProvider.getString("Enter new data: ");
        UserDao.getInstance().editUserDataById(mentorId, "UserDetails", columnToEdit, newParamData);
    }
}
