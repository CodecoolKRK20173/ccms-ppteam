package com.codecool.user;

import com.codecool.dao.UserDao;
import java.sql.SQLException;
import com.codecool.models.UserTypes;
import java.util.List;

public class Admin extends Employee {

    public Admin(int id, String name, String surname, String email, String password, UserTypes type) {
        super(id, name, surname, email, password, type);
    }

    public List<User> getMentorsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.MENTOR);
    }

    public void addMentor(String name, String surname, String email, String password) throws SQLException {
        UserDao.getInstance().addMentor(name, surname, email, password);
    }

    public void removeMentor(int mentorId) throws SQLException {
        UserDao.getInstance().removeUserById(mentorId);
    }

    public void editMentorsData(int id, String columnToEdit, String newParamData) {
        UserDao.getInstance().editUserDataById(id, "UserDetails", columnToEdit, newParamData);
    }

    @Override
    public List<User> getStudentsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.STUDENT);
    }
}
