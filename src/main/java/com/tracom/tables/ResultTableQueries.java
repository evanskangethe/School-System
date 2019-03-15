package com.tracom.tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ResultTableQueries {
    private static final String url = "jdbc:mysql://localhost:3306/campus";//database url

    Connection connection;//manage connection
    Statement createTable;//CreateTable
    Statement dropTable;//Drop Table

    //constructor
    public ResultTableQueries() {
        try {
            Properties info = new Properties();
            info.put("user","root");//database username
            info.put("password","");//database password
            connection = DriverManager.getConnection(url,info);//make connection

            createTable = connection.createStatement();
            dropTable = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    //create table
    public void up(){
        try {
            createTable.executeUpdate("CREATE TABLE student_results (" +
                    "id INT(6) UNSIGNED AUTO_INCREMENT," +
                    "regNumber VARCHAR(30) NOT NULL ,"+
                    "grade VARCHAR(2) NOT NULL,"+
                    "marks_attained INT(3) NOT NULL,"+
                    "createdOn DATE NOT NULL," +
                    "status VARCHAR(7)," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (regNumber) REFERENCES students(regNumber))");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //delete table
    public void down(){
        try {
            dropTable.executeUpdate("DROP TABLE student_results");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
