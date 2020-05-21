package com.codecool.user;

import com.codecool.models.UserTypes;

public abstract class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserTypes userType;

    public User(int id, String name, String surname, String email, String password, UserTypes type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypes getType() {
        return userType;
    }

    public void setType(UserTypes type) {
        this.userType = type;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s %s", id, name, surname, email, password, userType.toString().toString());
    }
}
