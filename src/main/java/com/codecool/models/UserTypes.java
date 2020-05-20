package com.codecool.models;

public enum UserTypes {
    ADMIN("admin"),
    STUDENT("student"),
    OFFICE_MEMBER("office member"),
    MENTOR("mentor"),
    NONE("none");

    String userType;

    public String toString() {
        return userType;
    }

    UserTypes(String userType) {
        this.userType = userType;
    }

}
