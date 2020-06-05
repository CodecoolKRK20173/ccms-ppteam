package com.codecool.dao;

import com.codecool.containers.UsersContainer;
import com.codecool.models.UserTypes;
import com.codecool.user.*;
import com.codecool.utilities.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.models.UserTypes.*;

public class UserDao extends Dao implements UserDaoInterface {

    public void initializeUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(getUsersByUserType(STUDENT));
        users.addAll(getUsersByUserType(MENTOR));
        users.addAll(getUsersByUserType(OFFICE_MEMBER));
        users.addAll(getUsersByUserType(ADMIN));
        UsersContainer.getInstance().setUserList(users);
    }

    @Override
    public List<User> getUsersByUserType(UserTypes userType) {
        List<User> users = new ArrayList<>();
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
            users = createUsersList(results);
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(String name, String surname, String email, String password, UserTypes userType) {
        connect();
        String tableType = "";
        while (tableType.equals("")) {
            tableType = setTableType(userType);
        }

        try {
            statement.executeUpdate("INSERT INTO UserDetails (name, surname, email, password, userType) " +
                    "VALUES('" + name + "', '" + surname + "', '" + email + "', '" + password + "', '" + userType + "');" +
                    "INSERT INTO " + tableType + " (userDetailsID) SELECT userDetailsID FROM UserDetails WHERE email LIKE '" + email + "';");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeUsers();
    }

    public ResultSet getAttendanceResultSet(int id) {
        connect();
        ResultSet result = null;
        try {
            result = statement.executeQuery("SELECT UserDetails.name, UserDetails.surname, Attendance.status, Attendance.date FROM UserDetails\n" +
                                                "JOIN Attendance ON userDetails.userDetailsID = Attendance.studentID\n" +
                                                "WHERE UserDetails.userDetailsID LIKE "+ id +";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void removeUserById(int id) {
        connect();
        try {
            statement.executeUpdate("begin;" +
                                        "DELETE FROM UserDetails WHERE userDetailsID ='" + id + "';" +
                                        "DELETE FROM Students WHERE userDetailsID ='" + id + "';" +
                                        "DELETE FROM Admins WHERE userDetailsID ='" + id + "';" +
                                        "DELETE FROM Mentors WHERE userDetailsID ='" + id + "';" +
                                        "DELETE FROM OfficeMembers WHERE userDetailsID ='" + id + "';" +
                                        "commit;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeUsers();
    }

    @Override
    public void editUserDataById(int id, String table, String paramToEdit, String newData) {
        connect();
        try {
            statement.executeUpdate("Update '" + table +
                                        "' SET '" + paramToEdit + "' = '" + newData + "' " +
                                        "WHERE userDetailsID = '" + id + "';");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeUsers();
    }


    private List<User> createUsersList(ResultSet results) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
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
                case "office":
                    user = new OfficeMember(id, name, surname, email, password, OFFICE_MEMBER);
                    users.add(user);
                    break;
            }
        }
        return users;
    }

    private String setTableType(UserTypes userType) {
        String tableType = "";
        switch (userType) {
            case STUDENT:
                tableType = "Students";
                break;
            case ADMIN:
                tableType = "Admins";
                break;
            case MENTOR:
                tableType = "Mentors";
                break;
            case OFFICE_MEMBER:
                tableType = "OfficeMembers";
                break;
            default:
                View.getInstance().print("Incorrect user type, please try again.");
        }
        return tableType;
    }
}