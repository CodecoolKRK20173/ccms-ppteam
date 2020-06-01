package com.codecool.containers;

import com.codecool.models.UserTypes;
import com.codecool.user.*;
import com.codecool.utilities.InputProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UsersContainer {
    private List<User> usersList;
    private static UsersContainer instance;

    public UsersContainer() {
        this.usersList = new ArrayList<>();
    }

    public static UsersContainer getInstance() {
        if (instance == null) {
            synchronized(InputProvider.class) {
                if (instance == null) {
                    instance = new UsersContainer();
                }
            }
        }
        return instance;
    }

    public List<User> getListByUserType(UserTypes userType) {
        return usersList.stream().filter(o -> o.getType().equals(userType)).collect(Collectors.toList());
    }

    public void setUserList(List<User> userList) {
        this.usersList = userList;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        for (User user:usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new NoSuchElementException("There isn't user with specified data in database");
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public void removeUserById(int id) {
        usersList.removeIf(user -> user.getId() == id);
    }

    public void editUserDataById(int id, String columnToEdit, String newParamData) {
//        usersList.stream().filter(user -> user.getId() == id).forEach(user -> user.);
//        ClassWithStuff myStuff = new ClassWithStuff();
//        Field[] fields = myStuff.getClass().getDeclaredFields();
        for (User user : usersList) {
            if (user.getId() == id) {
                for (Field field : UsersContainer.getInstance().getClass().getFields()) {
                    if (field.getName().equals(columnToEdit)) {
                        try {
                            field.set(user, newParamData);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean isUserOccursById(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserGivenType(UserTypes userType) {
        for (User user : usersList) {
            if (user.getType().equals(userType)) {
                return true;
            }
        }
        return false;
    }
}
