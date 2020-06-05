package com.codecool.dao;

import com.codecool.containers.UsersContainer;
import com.codecool.models.Assignment;
import com.codecool.models.AssignmentStatus;
import com.codecool.models.UserTypes;
import com.codecool.user.Mentor;
import com.codecool.user.Student;
import com.codecool.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao extends Dao{
    public List<Assignment> getAssignmentsByMentor(Mentor mentor){
        List<Assignment> list = new ArrayList<>();
        connect();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Assignments WHERE mentorID == " + mentor.getId());
            while (result.next()) {
                int id = result.getInt("id");
                String description = result.getString("name");
                Student student = (Student) UsersContainer.getInstance().getListByUserType(UserTypes.STUDENT).get(result.getInt("studentID"));
                AssignmentStatus status = AssignmentStatus.valueOf(result.getString("status"));
                LocalDate date = LocalDate.parse(result.getString("date"));
                list.add(new Assignment(id, mentor, student, description, status, date));
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Assignment> getAssignmentsByUser(Student student){
        List<Assignment> list = new ArrayList<>();
        connect();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Assignments WHERE studentID == " + student.getId());
            while (result.next()) {
                int id = result.getInt("id");
                String description = result.getString("name");
                Mentor mentor = (Mentor) UsersContainer.getInstance().getListByUserType(UserTypes.MENTOR).get(result.getInt("mentorID"));
                AssignmentStatus status = AssignmentStatus.valueOf(result.getString("status"));
                LocalDate date = LocalDate.parse(result.getString("date"));
                list.add(new Assignment(id, mentor, student, description, status, date));
                statement.close();
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ResultSet getAssignmentResultSet(User user) {
        connect();
        ResultSet result = null;
        try {
            switch (user.getType()){
                case MENTOR:
                    result = statement.executeQuery("SELECT * FROM Assignments WHERE mentorID == " + user.getId());
                    break;
                case STUDENT:
                    result = statement.executeQuery("SELECT * FROM Assignments WHERE studentID == " + user.getId());
                    break;
            }
//            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void addAssignment(Mentor mentor, Student student, String description){
        connect();
        try{
            statement.executeUpdate("INSERT INTO Assignments (name, studentID, mentorID, status, date)" +
                    "VALUES ('"+ description +"', '"+ student.getId() +"','"+ mentor.getId() +"', 'TODO', '"+ LocalDate.now() +"')");
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateAssignment(){

    }
}
