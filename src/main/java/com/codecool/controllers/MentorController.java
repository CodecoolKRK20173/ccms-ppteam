package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.models.AttendanceTypes;
import com.codecool.models.UserTypes;
import com.codecool.user.User;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MentorController {
    private static MentorController instance;
    private String[] menu = {"1.Show students list", "2.Add Assignment", "3.Grade an assignment", "4.Check Attendance" ,
            "5.Add student to class", "6.Remove Student from class", "7.Edit student data", "0.Exit"};

    public static MentorController getInstance() {
        if (instance == null) {
            synchronized(MentorController.class) {
                if (instance == null) {
                    instance = new MentorController();
                }
            }
        }
        return instance;
    }

    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInt("SELECT OPTION: ")){
                case 1:
                    View.getInstance().showUsersTable(UsersContainer.getInstance().getStudentsList());//view
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    checkAttendance();
                    break;
                case 5:
                    addStudentToClassroom();
                    break;
                case 6:
                    removeStudentFromClassroom();
                    break;
                case 7:
                    editStudentData();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    View.getInstance().wrongData();
            }

        }
    }

    private List<User> getStudentsList() {
        return UserDao.getInstance().getUsersByUserType(UserTypes.STUDENT);
    }

    private void addStudentToClassroom() {
        int studentId = InputProvider.getInt("Enter student id: ");
        String newClassroom = InputProvider.getString("Enter new classroom name: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", newClassroom);
    }

    private void removeStudentFromClassroom() {
        int studentId = InputProvider.getInt("Enter student id: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", null);
    }

    private void editStudentData() {
        int studentId = InputProvider.getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getString("Enter column to edit: ");
        String newParamData = InputProvider.getString("Enter new data: ");
        UserDao.getInstance().editUserDataById(studentId, "UserDetails", columnToEdit, newParamData);
    }

    private void checkAttendance() {
        for (User student : UsersContainer.getInstance().getStudentsList()) {
            View.getInstance().showUsersTable(UsersContainer.getInstance().getStudentsList());//view
            String attendanceType = "absent";
            boolean isRunning = true;
            while (isRunning) {
                switch (InputProvider.getInt("Enter student's attendance:\n(1) present\n (2) absent\n(0) exit")) {
                    case 1:
                        attendanceType = "present";
                        break;
                    case 2:
                        attendanceType = "absent";
                        break;
                    case 0:
                        isRunning = false;
                        break;
                    default:
                        View.getInstance().wrongData();
                }
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime today = LocalDateTime.now();
            UserDao.getInstance().addAttendance(student.getId(), attendanceType, dateFormat.format(today));
        }
    }
}
