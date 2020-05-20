package com.codecool.user;

import com.codecool.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

public class Admin extends User {
    UserDao userDao = new UserDao();

    public Admin(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public List<User> getMentorsList() {
        return userDao.getUsersList("mentor");
    }

    public void addMentor(int id, String name, String surname, String email, String password, String userType) throws SQLException {
        Mentor mentor = new Mentor(id, name, surname, email, password, userType);
        userDao.addMentor(mentor);
    }

    public void removeMentor(int mentorId) throws SQLException {
        userDao.removeUserById(mentorId);
    }

    public void editMentorsData(int id, String columnToEdit, String newParamData) {
        userDao.editUserDataById(id, "UserDetails", columnToEdit, newParamData);
    }
}
