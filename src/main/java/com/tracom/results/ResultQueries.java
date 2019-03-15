package com.tracom.results;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ResultQueries {

    private static final String url = "jdbc:mysql://localhost:3306/campus";//database url

    private Connection connection;//manage connection
    private PreparedStatement createStudentResults;//create student
    private PreparedStatement selectAllStudentsResults;//select all query
    private PreparedStatement selectStudentResultByRegNumber;//select with regNO
    private  ResultSet resultSet;//result set

    //input variables
    private List<StudentResult> studentResults;
    //constructor
    public ResultQueries() {
        try{
            Properties info = new Properties();
            info.put("user","root");//database username
            info.put("password","");//database password
            connection = DriverManager.getConnection(url,info);//make connection

            createStudentResults = connection.prepareStatement("INSERT INTO student_results (regNumber,grade,marks_attained,createdOn,status) VALUES (?,?,?,?,?)");//insert results
            selectAllStudentsResults = connection.prepareStatement("SELECT * FROM student_results");//select all results
            selectStudentResultByRegNumber = connection.prepareStatement("SELECT * FROM student_results WHERE regNumber = ?");//select by reg No
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    //get all results
    public List<StudentResult> getAllResults() {
        try{
            resultSet = selectAllStudentsResults.executeQuery();//execute query
            studentResults = new ArrayList<>();

            while (resultSet.next()){//iterate results
                studentResults.add(new StudentResult(
                        resultSet.getInt("id"),
                        resultSet.getInt("marks_attained"),
                        resultSet.getString("created_On"),
                        resultSet.getString("status"),
                        resultSet.getString("regNumber"),
                        resultSet.getString("grade")

                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                close();
            }
        }
        return studentResults;
    }

    //get student result by regNo
    public List<StudentResult> getStudentResults(String regNo) {
        try{
            selectStudentResultByRegNumber.setString(1,regNo);
            resultSet = selectStudentResultByRegNumber.executeQuery();
            studentResults =new ArrayList<>();

            while (resultSet.next()){
                studentResults.add(new StudentResult(
                        resultSet.getInt("id"),
                        resultSet.getInt("marks_attained"),
                        resultSet.getString("createdOn"),
                        resultSet.getString("status"),
                        resultSet.getString("regNumber"),
                        resultSet.getString("grade")

                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                close();
            }
        }
        return studentResults;
    }

    //add results
    public int addResults(int marks_attained, String createdOn, String status, String regNumber,String grade){
        int result  = 0;

        try{
            createStudentResults.setString(1,regNumber);
            createStudentResults.setString(2,grade);
            createStudentResults.setInt(3,marks_attained);
            createStudentResults.setString(4,createdOn);
            createStudentResults.setString(5,status);

            result = createStudentResults.executeUpdate();
        }catch (SQLException  e){
            e.printStackTrace();
        }

        return result;
    }

    //close connection
    private void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
