package com.codecool.dao;

import com.codecool.models.Assignment;
import com.codecool.user.Admin;
import com.codecool.user.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao extends Dao{
    public List<Assignment> getAssignmentsByMentor(Admin admin){
        List<Assignment> list = new ArrayList<>();
        connect();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Assignments WHERE mentorID == " + admin.getId());
            while (result.next()) {
//               list.add(new Assignment(
//                       result.getInt("id"),
//                       result.getString("name"),
//                       result.getInt("studentID")));
//                       result.getString("mentorID"),
//                       result.getString("name"),
                //todo create assignment list

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public List<Assignment> getAssignmentsByUser(Student student){
        List<Assignment> list = new ArrayList<>();
        connect();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Assignments WHERE mentorID == " + student.getId());
//            while (result.next()) {
//                list.add(new Assignment()
//todo create assignment list
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return null;
    }
    public void addAssignment() throws SQLException {
        connect();
        statement.executeUpdate("INSERT INTO Assignments (name, studentID, mentorID, status, date)" +
                "VALUES ()");//todo add values

    }
    public void updateAssignment(){

    }
}
