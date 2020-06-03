package com.codecool.models;

import com.codecool.user.Mentor;
import com.codecool.user.Student;

import java.time.LocalDate;

public class Assignment {
    private int id;
    private Student owner;
    private AssignmentStatus assignmentStatus;
    private Mentor mentor;
    private String description;
    private LocalDate date;

    public Assignment(int id,Mentor mentor,  Student student, String description){
        this.id = id;
        this.mentor = mentor;
        this.owner = student;
        this.description = description;
        this.assignmentStatus = AssignmentStatus.TODO;
        this.date = LocalDate.now();
    }

    public Student getOwner() {
        return owner;
    }

    public void setOwner(Student owner) {
        this.owner = owner;
    }

    public String getAssignmentStatus() {
        return assignmentStatus.toString();
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
