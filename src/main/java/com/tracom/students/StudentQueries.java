package com.tracom.students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentQueries {

    private static final String url = "jdbc:mysql://localhost:3306/campus";//database url

    private Connection connection;//manage connection
    private PreparedStatement createStudent;//create student
    private PreparedStatement selectAllStudent;//select all query
    private PreparedStatement selectStudentByRegNumber;//select with regNO
    private PreparedStatement deleteStudentByRegNumber;//delete using regNO
    private PreparedStatement updateStudentByRegNumber;//update
    private ResultSet resultSet;//result set

    //input variables
    private List<Student> students;

    //constructor
    public StudentQueries() {
        try{
            Properties info = new Properties();
            info.put("user","root");//database username
            info.put("password","");//database password
            connection = DriverManager.getConnection(url,info);//make connection

            createStudent = connection.prepareStatement("INSERT INTO students (firstName,lastName,nationalId,regNumber,createdOn) values (?,?,?,?,?)");//create student table
            selectAllStudent = connection.prepareStatement("SELECT * FROM students");//select all query
            selectStudentByRegNumber = connection.prepareStatement("SELECT * FROM students WHERE regNumber = ?");//select with regNO
            deleteStudentByRegNumber = connection.prepareStatement("DELETE FROM students WHERE regNumber = ?");//delete using regNO
            updateStudentByRegNumber = connection.prepareStatement("UPDATE students SET firstName = ?,lastName = ?,nationalId = ?,createdOn = ? WHERE regNumber = ?");//update


        }catch ( SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    //insert student details
    public  int addStudent(String firstName, String lastName, String nationalId, String regNumber, String createdOn){
        int result = 0;
        try {
            createStudent.setString(1,firstName);//insert firstName
            createStudent.setString(2,lastName);//insert lastName
            createStudent.setString(3,nationalId);//insert nationalId
            createStudent.setString(4,regNumber);//insert regNumber
            createStudent.setString(5,createdOn);//insert createdOn

            result = createStudent.executeUpdate();//execute query
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //select all the students in the databse
    public List<Student> getAllStudent () {
        try{
            resultSet = selectAllStudent.executeQuery();//execute query
            students = new ArrayList<>();

            while (resultSet.next()){
                students.add(new Student(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("nationalId"),
                        resultSet.getString("regNumber"),
                        resultSet.getString("createdOn")
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

        return students;
    }

    //select student by RegNO
    public List<Student> getStudentByRegNo(String regNo) {
        try {
            selectStudentByRegNumber.setString(1,regNo);//specify regNO
            resultSet = selectStudentByRegNumber.executeQuery();
            students = new ArrayList<>();

            while(resultSet.next()){//Iterate results
                students.add(new Student(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("nationalId"),
                        resultSet.getString("regNumber"),
                        resultSet.getString("createdOn")
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

        return  students;
    }

    //delete student by ReqNumber
    public int deleteStudent(String regNo) {
        int result = 0;
        try {
            deleteStudentByRegNumber.setString(1,regNo);//specify regNO
            result = deleteStudentByRegNumber.executeUpdate();//execute query
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  result;
    }

    //update student by RegNo
    public int updateStudent(String firstName, String lastName, String nationalId, String regNumber, String createdOn) {
        int result = 0;

        try{
            updateStudentByRegNumber.setString(1,firstName);
            updateStudentByRegNumber.setString(2,lastName);
            updateStudentByRegNumber.setString(3,nationalId);
            updateStudentByRegNumber.setString(4,createdOn);
            updateStudentByRegNumber.setString(5,regNumber);

            result = updateStudentByRegNumber.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //close connection
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
