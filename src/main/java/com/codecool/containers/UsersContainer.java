package com.codecool.containers;

import com.codecool.models.UserTypes;
import com.codecool.user.*;

import java.util.ArrayList;
import java.util.List;

public class UsersContainer {
    private List<User> userList;
    private List<User> studentsList;
    private List<User> mentorsList;
    private List<User> adminsList;
    private List<User> officeMembersList;

    public UsersContainer() {
        this.userList = new ArrayList<>();
        this.officeMembersList = new ArrayList<>();
        this.studentsList = new ArrayList<>();
        this.mentorsList = new ArrayList<>();
        this.adminsList = new ArrayList<>();
    }


    public List<User> getStudentsList() {
        return studentsList;
//        return studentsList.stream().filter(o -> o.getType().equals(UserTypes.STUDENT));
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
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
