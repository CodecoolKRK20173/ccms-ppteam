package com.codecool.user;

import com.codecool.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

public class Admin extends Employee {
    UserDao userDao = new UserDao();

    public Admin(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public List<User> getMentorsList() {
        return userDao.getUsersList("mentor");
    }

    public void addMentor(String name, String surname, String email, String password) throws SQLException {
        userDao.addMentor(name, surname, email, password);
    }


    public void removeMentor(int mentorId) throws SQLException {
        userDao.removeUserById(mentorId);
    }

    public void editMentorsData(int id, String columnToEdit, String newParamData) {
        userDao.editUserDataById(id, "UserDetails", columnToEdit, newParamData);
    }

    @Override
    public List<User> getStudentsList() {
        return userDao.getUsersList("student");
    }
}
