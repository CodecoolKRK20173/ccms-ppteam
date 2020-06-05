package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class AdminController {
    private final String[] menu = {"1.Show students list", "2.Show mentors list" , "3.Add new mentor", "4.Remove mentor", "5.Edit mentors data", "0.Exit"};
    private final UserDao userDao;
    
    AdminController() {
        this.userDao = new UserDao();
    }
    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInstance().getInt("SELECT OPTION: ")) {
                case 1:
                    showStudentsList();
                    break;
                case 2:
                    showMentorsList();
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

    private void showStudentsList() {
        View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT));
    }

    private void showMentorsList() {
        View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.MENTOR));
    }

    private void addMentor() {
        String name = InputProvider.getInstance().getString("Enter name: ");
        String surname = InputProvider.getInstance().getString("Enter surname: ");
        String email = InputProvider.getInstance().getEmail();
        String password = InputProvider.getInstance().getString("Enter password: ");
        UserTypes userType = UserTypes.MENTOR;
        userDao.addUser(name, surname, email, password, userType);
        View.getInstance().print(String.format("%s %s has been successfully added as %s", name, surname, userType));
    }

    private void removeMentor() {
        int mentorId = InputProvider.getInstance().getInt("Enter mentors id: ");
        if (checkIfUserWithGivenIdExist(mentorId, UserTypes.MENTOR)) {
            userDao.removeUserById(mentorId);
            UsersContainer.getInstance().removeUserById(mentorId);
            View.getInstance().print("Mentor has been successfully removed.");
        } else {
            View.getInstance().print(String.format("Mentor with given id: %d doesn't exist.%n", mentorId));
        }
    }

    private void editMentorsData() {
        int mentorId = InputProvider.getInstance().getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getInstance().getString("Enter column to edit: ").toLowerCase();
        String newParamData = InputProvider.getInstance().getString("Enter new data: ");
        if (checkIfUserWithGivenIdExist(mentorId, UserTypes.MENTOR)) {
            userDao.editUserDataById(mentorId, "UserDetails", columnToEdit, newParamData);
            UsersContainer.getInstance().editUserDataById(mentorId, columnToEdit, newParamData);
            View.getInstance().print("Mentors data has been successfully edited.");
        } else {
            View.getInstance().print(String.format("Mentor with given id: %d doesn't exist.%n", mentorId));
        }
    }

    private boolean checkIfUserWithGivenIdExist(int userId, UserTypes userType) {
        return UsersContainer.getInstance().isUserOccursById(userId) && UsersContainer.getInstance().isUserGivenType(userType);
    }
}
