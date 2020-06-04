package com.codecool.models;

public enum AssignmentStatus {
    TODO("ToDo"),
    TO_CHECK("to check"),
    DONE("done");

    String assignmentStatus;

    public String toString() {
        return assignmentStatus;
    }

    AssignmentStatus(String userType) {
        this.assignmentStatus = userType;
    }

    }
