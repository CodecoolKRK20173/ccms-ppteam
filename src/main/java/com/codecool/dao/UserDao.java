package com.codecool.dao;

import com.codecool.containers.UsersContainer;
import com.codecool.models.UserTypes;
import com.codecool.user.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.models.UserTypes.*;

public class UserDao extends Dao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized(UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public void initializeUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(getUsersByUserType(STUDENT));
        users.addAll(getUsersByUserType(MENTOR));
        users.addAll(getUsersByUserType(OFFICE_MEMBER));
        users.addAll(getUsersByUserType(ADMIN));
        UsersContainer.getInstance().setUserList(users);
    }

    public List<User> getUsersByUserType(UserTypes userType) {
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
                    case "office":
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

    public void addMentor(String name, String surname, String email, String password) throws SQLException {
        connect();
        statement.executeUpdate("INSERT INTO UserDetails (name, surname, email, password, userType) " +
                "VALUES('" + name + "', '" + surname + "', '" + email + "', '" + password + "', 'mentor');" +
                "INSERT INTO Mentors (userDetailsID) SELECT userDetailsID FROM UserDetails WHERE email LIKE '" + email + "';");
        statement.close();
        connection.close();
        initializeUsers();
    }

    public ResultSet getAttendanceResultSet(int id) throws SQLException {
        connect();
        ResultSet result = statement.executeQuery("SELECT UserDetails.name, UserDetails.surname, Attendance.status, Attendance.date FROM UserDetails\n" +
                "                    JOIN Attendance ON userDetails.userDetailsID = Attendance.studentID\n" +
                "                    WHERE UserDetails.userDetailsID LIKE "+ id +";");
        return result;
    }

    public void removeUserById(int id) throws SQLException {
        connect();
        statement.executeUpdate("begin;" +
                                    "DELETE FROM UserDetails WHERE userDetailsID ='" + id + "';" +
                                    "DELETE FROM Students WHERE userDetailsID ='" + id + "';" +
                                    "DELETE FROM Admins WHERE userDetailsID ='" + id + "';" +
                                    "DELETE FROM Mentors WHERE userDetailsID ='" + id + "';" +
                                    "DELETE FROM OfficeMembers WHERE userDetailsID ='" + id + "';" +
                                    "commit;");
        statement.close();
        connection.close();
        initializeUsers();
    }

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

    public void addAttendance(int studentId, String status, String date) {
        connect();
        try {
            statement.executeUpdate("INSERT INTO Attendance (studentID, status, date) " +
                    "VALUES('" + studentId + "', '" + status + "', '" + date + "');");
            statement.close();
            connection.close();
            initializeUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeUsers();
    }
}