package com.codecool.user;

import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;

import java.util.List;

public abstract class Employee extends User {

    public Employee(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }

    public abstract List<User> getStudentsList();
}
