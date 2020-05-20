package com.codecool.dao;

import com.codecool.models.UserTypes;
import com.codecool.user.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codecool.models.UserTypes.*;

public class UserDao extends Dao {

    public User getUserByEmailandPassword(String email, String password) {
        List<User> users = getUsers();
        connect();
        int id;
        try {
            ResultSet results = statement.executeQuery ("SELECT * FROM UserDetails WHERE email = '"+
                    email +"' and password = '"+ password +"';");
            if (results != null) {
                id = results.getInt("UserDetailsID")-1;
                results.close();
                statement.close();
                connection.close();
                return users.get(id);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {}
        throw new NoSuchElementException("There isn't user with specified data in database");
    }

    private List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(getGivenTypeOfUsersList(STUDENT));
        users.addAll(getGivenTypeOfUsersList(MENTOR));
        users.addAll(getGivenTypeOfUsersList(OFFICE_MEMBER));
        users.addAll(getGivenTypeOfUsersList(ADMIN));
        return users;
    }

    public List<User> getGivenTypeOfUsersList(UserTypes userType) {
        List<User> users = new ArrayList<>();
        User user;
        connect();
        try {
            ResultSet results;
            if (userType.equals(STUDENT)) {
                results = statement.executeQuery("SELECT UserDetails.*, Students.classroom FROM UserDetails" +
                        "                        JOIN Students ON userDetails.userDetailsID = Students.userDetailsID" +
                        "                        WHERE userType LIKE 'student';");
            } else {
                results = statement.executeQuery("SELECT * FROM UserDetails WHERE userType LIKE '" + userType.toString() + "';");
            }
            while (results.next()) {
                int id = results.getInt("UserDetailsID");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String email = results.getString("email");
                String password = results.getString("password");
                String type = results.getString("userType");
                switch (type){
                    case "student":
                        String classroom = results.getString("classroom");
                        user = new Student(id, name, surname, email, password, STUDENT, classroom);
                        users.add(user);
                        break;
                    case "mentor":
                        user = new Mentor(id, name, surname, email, password, MENTOR);
                        users.add(user);
                        break;
                    case "admin":
                        user = new Admin(id, name, surname, email, password, ADMIN);
                        users.add(user);
                        break;
                    case "office member":
                        user = new OfficeMember(id, name, surname, email, password, OFFICE_MEMBER);
                        users.add(user);
                        break;
                }
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