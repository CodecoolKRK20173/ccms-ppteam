package com.codecool.dao;

import com.codecool.user.Mentor;
import com.codecool.user.OfficeMember;
import com.codecool.user.Student;
import com.codecool.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    public List<User> getUsersList(String userType) {
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
                results = statement.executeQuery("SELECT * FROM UserDetails " +
                                                    "WHERE userType LIKE '" + userType + "';");
            }
            while (results.next()) {
                int id = results.getInt("userDetailsId");
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

    public void addMentor(Mentor mentor) throws SQLException {
        connect();
        statement.executeUpdate("INSERT INTO UserDetails (name, surname, email, password, userType, ) " +
                "VALUES('" + mentor.getName() + "', '" + mentor.getSurname() + "', '" + mentor.getEmail() + "', '" + mentor.getPassword() + "', '" + mentor.getType() + "');" +
                "INSERT INTO Mentors (userDetailsId) SELECT userDetailsId WHERE email LIKE '" + mentor.getEmail() + "';");
        statement.close();
        connection.close();
    }

//    public void removeUser(String name, String userType) throws SQLException {//ewentualnie dolozyc surname dla pewnosci
//        connect();
//        if (userType.equals("student")) {
//            statement.executeUpdate("DELETE FROM User WHERE name ='" + name + "' AND type = mentor;");
//        }
//        statement.executeUpdate("DELETE FROM User WHERE name ='" + name + "' AND type = mentor;");
//        statement.close();
//        connection.close();
//    }

    public void removeUserById(int id) throws SQLException {
        connect();
        statement.executeUpdate("begin;" +
                                    "DELETE FROM UserDetails WHERE id ='" + id + "';" +
                                    "DELETE FROM Students WHERE id ='" + id + "';" +
                                    "DELETE FROM Attendance WHERE id ='" + id + "';" +
                                    "DELETE FROM Assignments WHERE id ='" + id + "';" +
                                    "DELETE FROM Admins WHERE id ='" + id + "';" +
                                    "DELETE FROM Mentors WHERE id ='" + id + "';" +
                                    "DELETE FROM OfficeMembers WHERE id ='" + id + "';" +
                                    "commit;");
        statement.close();
        connection.close();
    }

    public void editUserDataById(int id, String table, String paramToEdit, String newData) {
        connect();
        try {
            statement.executeUpdate("Update '" + table +
                                        "' SET '" + paramToEdit + "' = '" + newData + "' " +
                                        "WHERE userDetailsId = '" + id + "';");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}