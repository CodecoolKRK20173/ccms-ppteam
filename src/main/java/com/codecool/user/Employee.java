package com.codecool.user;

import com.codecool.dao.UserDao;

import java.util.List;

public abstract class Employee extends User {
    UserDao userDao = new UserDao();

    public Employee(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public List<User> getStudentsList() {
        return userDao.getUsersList("student");
    }
}
