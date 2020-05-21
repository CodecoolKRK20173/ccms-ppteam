package com.codecool.user;

import com.codecool.dao.UserDao;
import java.util.List;
import com.codecool.models.UserTypes;

public class Mentor extends Employee {

    public Mentor(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }

    public void addStudentToClassroom(int id, String newClassroom) {
       UserDao.getInstance().editUserDataById(id, "Students", "classroom", newClassroom);
    }

    public void removeStudentFromClassroom(int id) {
        UserDao.getInstance().editUserDataById(id, "Students", "classroom", null);
    }

    public void editStudentData(int id, String columnName, String newData) {
        UserDao.getInstance().editUserDataById(id, "UserDetails", columnName, newData);
    }

    @Override
    public List<User> getStudentsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.STUDENT);
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
