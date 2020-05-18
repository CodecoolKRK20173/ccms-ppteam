package com.codecool.user;

import java.util.List;

public class Admin extends User {

    public Admin(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
    }

    public List<Mentor> getMentorsList() {
        //TODO
        return null;
    }

    public void addMentor() {
        //TODO
    }

    public void removeMentor() {
        //TODO
    }

    public void editMentorsData() {
        //TODO
    }
}
