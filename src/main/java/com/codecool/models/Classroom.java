package com.codecool.models;

import com.codecool.user.Student;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private List<Student> studentsClassroom;

    public Classroom() {
        this.studentsClassroom = new ArrayList<>();
    }

    public List<Student> getStudentsClassroom() {
        return studentsClassroom;
    }

    public void setStudentsClassroom(List<Student> studentsClassroom) {
        this.studentsClassroom = studentsClassroom;
    }
}
