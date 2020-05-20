package com.codecool.user;

import com.codecool.models.Classroom;
import com.codecool.models.Grade;
import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private List<Grade> grades;
    private String classroom;

    public Student(int id, String name, String surname, String email, String password, String type, String classroom) {
        super(id, name, surname, email, password, type);
        this.classroom = classroom;
        this.grades = new ArrayList<>();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

//    public Classroom getClassroom() {
//        return classroom;
//    }
//
//    public void setClassroom(Classroom classroom) {
//        this.classroom = classroom;
//    }

    @Override
    public String toString() { // dorzucic classroma
        return String.format("%d %s %s %s %s %s %s", this.getId(), this.getName(), this.getSurname(), this.getEmail(), this.getPassword(), this.getType());
    }
}
