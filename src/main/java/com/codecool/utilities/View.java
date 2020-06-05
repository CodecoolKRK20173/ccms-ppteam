package com.codecool.utilities;

import com.codecool.dao.AssignmentDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Assignment;
import com.codecool.models.UserTypes;
import com.codecool.user.Student;
import com.codecool.user.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View {
    private static View instance;
    private AssignmentDao assignmentDao = new AssignmentDao();

    public static View getInstance() {
        if (instance == null) {
            synchronized(View.class) {
                if (instance == null) {
                    instance = new View();
                }
            }
        }
        return instance;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void wrongData() {
        System.out.println("Invalid Data.");
    }

    public void showMenu(String[] strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
    public void showAttendanceTable(int id) {
        try {
            ResultSet result = UserDao.getInstance().getAttendanceResultSet(id);
            System.out.println(FlipTableConverters.fromResultSet(result));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showAssignmentTable(User user) {
        try {
            ResultSet result = assignmentDao.getAssignmentResultSet(user);
            System.out.println(FlipTableConverters.fromResultSet(result));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showUsersTable(List<User> users) {
        if (users.get(0).getType().equals(UserTypes.STUDENT)){
            String[] headers = {"Id", "Name", "Surname", "Email", "Password", "Type", "Classroom"};
            ArrayList<String[]> allObj = new ArrayList<>();
            for (User user : users) {
                String[] object = {Integer.toString(user.getId()), user.getName(), user.getSurname()
                        , user.getEmail(), user.getPassword(), user.getType().toString(), ((Student) user).getClassroom()};

                allObj.add(object);
            }
            String[][] obj = allObj.toArray(new String[][]{});
            System.out.println(FlipTableConverters.fromObjects(headers, obj));
        } else {
            String[] headers = {"Id", "Name", "Surname", "Email", "Password", "Type"};
            ArrayList<String[]> allObj = new ArrayList<>();
            for (User user : users) {
                String[] object = {Integer.toString(user.getId()), user.getName(), user.getSurname()
                        , user.getEmail(), user.getPassword(), user.getType().toString()};
                allObj.add(object);
            }
            String[][] obj = allObj.toArray(new String[][]{});
            System.out.println(FlipTableConverters.fromObjects(headers, obj));
        }
    }

    public void print(String prompt) {
        System.out.println(prompt);
    }
}
