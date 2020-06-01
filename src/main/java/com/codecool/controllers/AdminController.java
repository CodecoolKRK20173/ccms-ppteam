package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

public class AdminController {
    private static AdminController instance;
    private final String[] menu = {"1.Show students list", "2.Show mentors list" , "3.Add new mentor", "4.Remove mentor", "5.Edit mentors data", "0.Exit"};

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

    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInt("SELECT OPTION: ")) {
                case 1:
//                    View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT));
                    showStudentsList();
                    break;
                case 2:
//                    View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.MENTOR));
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

//    private List<User> getStudentsList() {
//        return UserDao.getInstance().getUsersByUserType(UserTypes.STUDENT);
//    }
    private void showStudentsList() {
        View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT));
    }

//    private List<User> getMentorsList() {
//        return UserDao.getInstance().getUsersByUserType(UserTypes.MENTOR);
//    }
    private void showMentorsList() {
        View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.MENTOR));
    }

    private void addMentor() {
        String name = InputProvider.getString("Enter name: ");
        String surname = InputProvider.getString("Enter surname: ");
        String email = InputProvider.getEmail();
        String password = InputProvider.getString("Enter password: ");
        UserTypes userType = UserTypes.MENTOR;
        UserDao.getInstance().addUser(name, surname, email, password, userType);
        View.getInstance().print(String.format("%s %s has been successfully added as %s", name, surname, userType));
//TODO        UsersContainer.getInstance().addUser(new Mentor(name, surname, email, password, userType)); //zeby nadac id i je pobrac do dodania w user container i tak trzeba wejsc do bazy
    }

    private void removeMentor() {
        int mentorId = InputProvider.getInt("Enter mentors id: ");
        if (checkIfUserWithGivenIdExist(mentorId, UserTypes.MENTOR)) {
            UserDao.getInstance().removeUserById(mentorId);
            UsersContainer.getInstance().removeUserById(mentorId);
            View.getInstance().print("Mentor has been successfully removed.");
        } else {
            View.getInstance().print(String.format("Mentor with given id: %d doesn't exist.%n", mentorId));
        }
//        UsersContainer.getInstance().isUserOccursById(mentorId) ? UserDao.getInstance().removeUserById(mentorId) : View.getInstance().print(String.format("User with given %d id doesn't exist.", mentorId));
    }

    private void editMentorsData() {
        int mentorId = InputProvider.getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getString("Enter column to edit: ").toLowerCase();
        String newParamData = InputProvider.getString("Enter new data: ");
        if (checkIfUserWithGivenIdExist(mentorId, UserTypes.MENTOR)) {
            UserDao.getInstance().editUserDataById(mentorId, "UserDetails", columnToEdit, newParamData);
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
