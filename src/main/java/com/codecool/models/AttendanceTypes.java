package com.codecool.models;

public enum AttendanceTypes {
    PRESENT("present"),
    ABSENT("absent"),
    DELAY("delay");

    String attendanceTypes;

    AttendanceTypes(String attendanceTypes) {
        this.attendanceTypes = attendanceTypes;
    }

    public String toString() {
        return attendanceTypes;
    }
}
