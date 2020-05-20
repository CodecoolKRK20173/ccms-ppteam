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