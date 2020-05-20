package com.codecool.user;

import com.codecool.dao.UserDao;

import java.util.List;

public class Mentor extends Employee {
    UserDao userDao = new UserDao();

    public Mentor(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public void addStudentToClassroom(int id, String newClassroom) {
       userDao.editUserDataById(id, "Students", "classroom", newClassroom);
    }

    public void removeStudentFromClassroom(int id) {
        userDao.editUserDataById(id, "Students", "classroom", null);
    }

    public void editStudentData(int id, String columnName, String newData) {
        userDao.editUserDataById(id, "UserDetails", columnName, newData);
    }

    @Override
    public List<User> getStudentsList() {
        return userDao.getUsersList("student");
    }

    public void addAssignment() {
        //TODO
    }

    public void gradeAssignment() {
        //TODO
    }

    public void checkAttendence() {
        //TODO
    }

}
