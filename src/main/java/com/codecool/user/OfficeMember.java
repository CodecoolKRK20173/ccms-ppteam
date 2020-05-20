package com.codecool.user;

import com.codecool.dao.UserDao;

import java.util.List;

public class OfficeMember extends Employee {
    UserDao userDao = new UserDao();

    public OfficeMember(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    @Override
    public List<User> getStudentsList() {
        return userDao.getUsersList("student");
    }
}
