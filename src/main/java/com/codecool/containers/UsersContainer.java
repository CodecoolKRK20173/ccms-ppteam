package com.codecool.containers;

import com.codecool.user.*;
import com.codecool.utilities.InputProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UsersContainer {
    private List<User> usersList;
    private List<User> studentsList;
    private List<User> mentorsList;
    private List<User> adminsList;
    private List<User> officeMembersList;
    private static UsersContainer instance;

    public UsersContainer() {
        this.usersList = new ArrayList<>();
        this.officeMembersList = new ArrayList<>();
        this.studentsList = new ArrayList<>();
        this.mentorsList = new ArrayList<>();
        this.adminsList = new ArrayList<>();
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


    public List<User> getStudentsList() {
        return studentsList;
//   TODO     return studentsList.stream().filter(o -> o.getType().equals(UserTypes.STUDENT));
    }

    public void setStudentsList(List<User> studentsList) {
        this.studentsList = studentsList;
    }

    public List<User> getMentorsList() {
        return mentorsList;
    }

    public void setMentorsList(List<User> mentorsList) {
        this.mentorsList = mentorsList;
    }

    public List<User> getAdminsList() {
        return adminsList;
    }

    public void setAdminsList(List<User> adminsList) {
        this.adminsList = adminsList;
    }

    public List<User> getOfficeMembersList() {
        return officeMembersList;
    }

    public void setOfficeMembersList(List<User> officeMembersList) {
        this.officeMembersList = officeMembersList;
    }

    public List<User> getUserList() {
        return usersList;
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
}
