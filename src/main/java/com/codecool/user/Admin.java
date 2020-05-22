package com.codecool.user;

import com.codecool.dao.UserDao;
import java.sql.SQLException;
import com.codecool.models.UserTypes;
import java.util.List;

public class Admin extends Employee {

    public Admin(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }

    @Override
    public String getClassroom() {
        return null;
    }
}
