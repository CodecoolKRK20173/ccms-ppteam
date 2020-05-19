package com.codecool;

import com.codecool.dao.UserDao;

public class App 
{
    public static void main( String[] args )
    {
        UserDao userDao = new UserDao();
        System.out.println(userDao.getGivenTypeOfUsersList("student"));
    }
}
