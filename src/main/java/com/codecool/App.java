package com.codecool;

import com.codecool.models.MenuHandler;
import com.codecool.user.Admin;
import com.codecool.user.User;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        //testowo
        Admin admin = new Admin(5, "jan", "kowalski", "ja", "asd", "admin");
        List<User> list = admin.getMentorsList();
        for (User user : list) {
            System.out.println(user);
        }

        MenuHandler menuHandler = new MenuHandler();
        while (menuHandler.isRunning) {
            menuHandler.mainMenu();
        }
    }
}
