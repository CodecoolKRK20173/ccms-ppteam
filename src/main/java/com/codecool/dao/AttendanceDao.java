package com.codecool.dao;

import com.codecool.models.AttendanceTypes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDao extends Dao {

    public void addAttendance(int studentId, AttendanceTypes status, String date) {
        connect();
        try {
            statement.executeUpdate("INSERT INTO Attendance (studentID, status, date) " +
                    "VALUES('" + studentId + "', '" + status + "', '" + date + "');");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO wyrzuca wyjatek, pobrac z query liczbe i obsluzyc
    public boolean checkIfUserHadCheckedAttendanceToday(int userID, String date) {
        connect();
        boolean bool;
        try {
            ResultSet result = statement.executeQuery("SELECT EXISTS (SELECT date FROM Attendance" +
                                      "WHERE studentID LIKE '" + userID + "" +
                                      "AND date LIKE '" + date + "'');");

            // tutaj if obslugujacy wynik query
            result.close();
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
//        return bool;
    }

}
