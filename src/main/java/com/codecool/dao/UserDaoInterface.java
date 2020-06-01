package com.codecool.dao;

import com.codecool.models.UserTypes;
import com.codecool.user.User;

import java.util.List;

public interface UserDaoInterface {
    public List<User> getUsersByUserType(UserTypes userType);
    public void addUser(String name, String surname, String email, String password, UserTypes userType);
    public void removeUserById(int id);
    public void editUserDataById(int id, String table, String paramToEdit, String newData);
}
