package com.codecool.user;

import com.codecool.models.UserTypes;

public abstract class Employee extends User {

    public Employee(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }
}
