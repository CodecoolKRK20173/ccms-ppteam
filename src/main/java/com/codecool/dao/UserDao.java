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
        User user;
        connect();

        try {
            ResultSet results;
            if (userType.equals("student")) {
                results = statement.executeQuery("SELECT UserDetails.*, Students.classroom FROM UserDetails" +
                        "JOIN Students ON userDetails.id = userDetailsID" +
                        "WHERE userType LIKE 'student';");
            } else {
                results = statement.executeQuery("SELECT * FROM Employees WHERE type LIKE '" + userType + "';");
            }
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                String type = results.getString("userType");
                if (type.equals("student")) {
                    String classroom = results.getString("classroom");
                    user = new Student(id, name, surname, email, password, type, classroom);
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

    public void addUser(User givenUser) throws SQLException {
        connect();
        if (givenUser.getType().equals("student")) {
            statement.executeUpdate("INSERT INTO Students (name, surname, email, password, type) VALUES('" + givenUser.getName() + "', '"
                    + givenUser.getSurname() + "', '" + givenUser.getEmail() + "', '" + givenUser.getPassword() + "', '" + givenUser.getType() + "');");
        } else {
            statement.executeUpdate("INSERT INTO Employees (name, surname, email, password, type) VALUES('" + givenUser.getName() + "', '"
                    + givenUser.getSurname() + "', '" + givenUser.getEmail() + "', '" + givenUser.getPassword() + "', '" + givenUser.getType() + "');");
        }
        statement.close();
        connection.close();
    }

    public void removeUser(String name, String userType) throws SQLException {//ewentualnie dolozyc surname dla pewnosci
        connect();
        if (userType.equals("student")) {
            statement.executeUpdate("DELETE FROM User WHERE name ='" + name + "' AND type = mentor;");
        }
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