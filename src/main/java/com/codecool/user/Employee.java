package com.codecool.user;

import java.util.List;

public abstract class Employee extends User {

    public Employee(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public List<Student> getStudentsList() {
        //TODO
        return null;
    }
}
