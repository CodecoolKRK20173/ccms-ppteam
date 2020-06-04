package com.codecool.user;

import com.codecool.models.UserTypes;

public class Admin extends Employee {

    public Admin(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }

}
