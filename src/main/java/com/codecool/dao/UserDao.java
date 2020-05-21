package com.codecool.dao;

import com.codecool.containers.UsersContainer;
import com.codecool.models.UserTypes;
import com.codecool.user.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        UsersContainer.getInstance().setStudentsList(getUsersByUserType(STUDENT));
        UsersContainer.getInstance().setMentorsList(getUsersByUserType(MENTOR));
        UsersContainer.getInstance().setOfficeMembersList(getUsersByUserType(OFFICE_MEMBER));
        UsersContainer.getInstance().setAdminsList(getUsersByUserType(ADMIN));
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
        statement.executeUpdate("INSERT INTO UserDetails (name, surname, email, password, userType, ) " +
                "VALUES('" + name + "', '" + surname + "', '" + email + "', '" + password + "', mentor);" +
                "INSERT INTO Mentors (userDetailsId) SELECT userDetailsId WHERE email LIKE '" + email + "';");
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