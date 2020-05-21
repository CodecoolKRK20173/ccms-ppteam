package com.codecool;

import com.codecool.dao.UserDao;
import com.codecool.models.MenuController;
import com.codecool.models.UserTypes;
import com.codecool.user.Admin;
import com.codecool.user.User;

import java.util.List;

import static com.codecool.models.UserTypes.MENTOR;
import static com.codecool.models.UserTypes.STUDENT;

public class App
{
    public static void main( String[] args )
    {
        System.out.println(UserDao.getInstance().getUsersByUserType(MENTOR));
        MenuController menuController = new MenuController();
        while (menuController.isRunning) {
            menuController.mainMenu();
        }
    }
}
