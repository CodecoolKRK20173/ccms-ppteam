package com.codecool.utilities;

import com.codecool.dao.UserDao;
import com.codecool.models.UserTypes;
import com.codecool.user.User;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View {
    private static View instance;

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

    public void showUsersTable(List<User> users) {
        String[] headers = {"Id","Password", "Name", "Email", "Surname", "Type"};
        ArrayList<String[]> allObj = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
                String[] object = {Integer.toString(users.get(i).getId()),users.get(i).getName(),users.get(i).getSurname()
                        ,users.get(i).getEmail(),users.get(i).getPassword(),users.get(i).getType().toString()};
                allObj.add(object);
        }
        String[][] obj = allObj.toArray(new String[][] {});
        System.out.println(FlipTableConverters.fromObjects(headers, obj));
    }
}
