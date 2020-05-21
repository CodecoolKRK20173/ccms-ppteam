package com.codecool.user;

import com.codecool.dao.UserDao;
import java.util.List;
import com.codecool.models.UserTypes;

public class Mentor extends Employee {

    public Mentor(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }
}
