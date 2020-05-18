package com.codecool.dao;

import com.codecool.user.Mentor;
import com.codecool.user.Student;
import com.codecool.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    public List<User> getGivenTypeOfUsersList(String userType) {
        List<User> users = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users WHERE type LIKE '" + userType + "';");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                String type = results.getString("type");
                User user;
                if (type.equals("Student")) {
                    user = new Student(id, name, surname, email, password, type);
                } else {
                    user = new Mentor(id, name, surname, email, password, type);
                }
                users.add(user);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // przemyslec uniwersalne metody
    public void addMentor(Object givenMentor) throws SQLException {
        User user = (Mentor) givenMentor;
        connect();
        statement.executeUpdate("INSERT INTO User (name, surname, email, password, type) VALUES('" + user.getName() + "', '"
                + user.getSurname() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getType() + "');");
        statement.close();
        connection.close();
    }

    public void removeMentor(String name) throws SQLException {//ewentualnie dolozyc surname dla pewnosci
        connect();
        statement.executeUpdate("DELETE FROM User WHERE name ='" + name + "' AND type = mentor;");
        statement.close();
        connection.close();
    }

    public void editMentorsData(String paramToEdit, String previousData, String newData) {
        connect();
        try {
            statement.executeUpdate("Update User SET '" + paramToEdit + "' = '" + newData + "' " +
                                        "WHERE '" + paramToEdit + "' = '" + previousData + "';");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
