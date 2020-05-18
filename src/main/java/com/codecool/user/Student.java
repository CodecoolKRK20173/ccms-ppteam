package com.codecool.user;

import java.util.ArrayList;

public class Student extends User{
    private List<Grade> grades;
    private Classroom classroom;

    public Student(int id, String name, String surname, String email, String password, String type) {
        super(id, name, surname, email, password, type);
        this.grades = new ArrayList<>();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
