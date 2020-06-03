package com.codecool.controllers;

import com.codecool.containers.UsersContainer;
import com.codecool.dao.UserDao;
import com.codecool.models.Assignment;
import com.codecool.models.UserTypes;
import com.codecool.user.Mentor;
import com.codecool.user.Student;
import com.codecool.user.User;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MentorController extends Menu{
    private Mentor mentor;
    private final String[] menu = {"1.Show students list", "2.Add Assignment", "3.Grade an assignment", "4.Check Attendance" ,
            "5.Add student to class", "6.Remove Student from class", "7.Edit student data", "0.Exit"};

    MentorController(User user){
        this.mentor = (Mentor) user;
    }

    public void menu() {
        boolean isRunning = true;
        while (isRunning){
            View.getInstance().showMenu(menu);
            switch (InputProvider.getInstance().getInt("SELECT OPTION: ")){
                case 1:
                    View.getInstance().showUsersTable(UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT));
                    break;
                case 2:
                    addAssignment();
                    break;
                case 3:
                    submitAssignment();
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

    private void submitAssignment() {

    }

    private void addAssignment() {
        int id = InputProvider.getInstance().getInt("Which student you want to assign a assignment?(id): ");
        try{
            Student student;
            student = (Student) UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT).get(id);
//            Assignment assignment = new Assignment(mentor, student);
            //TODO dao add
            //TODO view -> added successfully
        }catch (Exception e) {
            System.out.println("This user isn't student");;
        }
    }


    private void addStudentToClassroom() {
        int studentId = InputProvider.getInstance().getInt("Enter student id: ");
        String newClassroom = InputProvider.getInstance().getString("Enter new classroom name: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", newClassroom);
    }

    private void removeStudentFromClassroom() {
        int studentId = InputProvider.getInstance().getInt("Enter student id: ");
        UserDao.getInstance().editUserDataById(studentId, "Students", "classroom", null);
    }

    private void editStudentData() {
        int studentId = InputProvider.getInstance().getInt("Enter mentors id: ");
        String columnToEdit = InputProvider.getInstance().getString("Enter column to edit: ");
        String newParamData = InputProvider.getInstance().getString("Enter new data: ");
        UserDao.getInstance().editUserDataById(studentId, "UserDetails", columnToEdit, newParamData);
    }

    private void checkAttendance() {
        for (User student : UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT)) {
            List<User> user = new ArrayList<>();
            user.add(student);
            View.getInstance().showUsersTable(user);
            String attendanceType = "absent";
                switch (InputProvider.getInstance().getInt("Enter student's attendance:\n(1) Present\n(2) Absent\n(0) Skip\nChoice: ")) {
                    case 1:
                        attendanceType = "present";
                        break;
                    case 2:
                        attendanceType = "absent";
                        break;
                    case 0:
                        break;
                    default:
                        View.getInstance().wrongData();
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime today = LocalDateTime.now();
            UserDao.getInstance().addAttendance(student.getId(), attendanceType, dateFormat.format(today));
        }
    }
}
