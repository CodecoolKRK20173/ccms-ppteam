package com.codecool.models;

public class Attendance {
    private int studentId;
    private String status;
    private String date;

    public Attendance(int studentId, String status, String date) {
        this.studentId = studentId;
        this.status = status;
        this.date = date;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
