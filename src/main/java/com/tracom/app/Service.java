package com.tracom.app;

import com.tracom.results.ResultQueries;
import com.tracom.students.Grading;
import com.tracom.students.Student;
import com.tracom.students.StudentQueries;
import com.tracom.tables.ResultTableQueries;
import com.tracom.results.StudentResult;
import com.tracom.tables.TableQueries;

import java.util.List;
import java.util.Scanner;

public class Service {
    private TableQueries students;
    private ResultTableQueries student_results;
    private StudentQueries stdQueries;
    private ResultQueries resultQueries;
    private Grading grading;

    //input varibles
    private String firstName, lastName, regNo, createdOn, nationalId, status, grade;
    private int marks_attained;
    private List<StudentResult> list;

    private Scanner input;

    public Service() {

        students = new TableQueries();
        student_results = new ResultTableQueries();
        stdQueries = new StudentQueries();
        resultQueries = new ResultQueries();
        grading = new Grading();

        input = new Scanner(System.in);
    }

    //create tables
    public void createTable() {
        System.out.println("Create tables\n");
        students.up();
        student_results.up();
        System.out.println("Tables Created successfully");
    }

    //Drop Tables
    public void dropTable() {
        System.out.println("Drop tables\n");
        students.down();
        student_results.down();
        System.out.println("Tables Droped successfully");
    }

    //register students
    public void registerStudent() {
        System.out.printf("%s\n", "Enter students details: ");
        do {
            System.out.printf("%s\n", "Enter First Name: ");
            firstName = input.next();
            System.out.printf("%s\n", "Enter Last Name: ");
            lastName = input.next();
            System.out.printf("%s\n", "Enter Registration Number: ");
            regNo = input.next();
            System.out.printf("%s\n", "Enter Date: ");
            createdOn = input.next();
            System.out.printf("%s\n", "Enter National ID: ");
            nationalId = input.next();

            int confirm = stdQueries.addStudent(firstName, lastName, nationalId, regNo, createdOn);

            if (confirm == 1) {
                System.out.printf("%s\n", "Student saved successfully");
            }
            System.out.printf("%s\n", "Stop entry press -1 ");
        } while ((input.nextInt()) != -1);
    }

    //display student
    public void showStudent() {
        String regNo;

        System.out.printf("%s\n", "Enter student Registration Number: ");
        regNo = input.next();

        Student student = stdQueries.getStudentByRegNo(regNo).get(0);//get student details

        System.out.printf("%30s%30s%30s%30s%n", "First Name", "Last Name", "National ID", "Created On");
        System.out.printf("%30s%30s%30s%30s", student.getFirstName(), student.getLastName(), student.getNationalId(), student.getCreatedOn());

    }

    //delete student
    public void deleteStudent() {
        do {
            System.out.printf("%s\n", "Enter Registration to delete record: ");
            regNo = input.next();
            int delete = stdQueries.deleteStudent(regNo);//delete
            if (delete == 1) {
                System.out.printf("%s\n", "Deleted successfully");
            }
            System.out.printf("%s\n", "Stop press -1 ");

        } while ((input.nextInt()) != -1);
    }

    //update student details
    public void updateStudent() {
        System.out.printf("%s\n", "Update student details by regNumber: ");
        regNo = input.next();

        System.out.printf("%s\n", "Enter First Name: ");
        firstName = input.next();
        System.out.printf("%s\n", "Enter Last Name: ");
        lastName = input.next();
        System.out.printf("%s\n", "Enter National ID: ");
        nationalId = input.next();
        System.out.printf("%s\n", "Enter Date: ");
        createdOn = input.next();

        int confirm = stdQueries.updateStudent(firstName, lastName, nationalId, regNo, createdOn);

        if (confirm == 1) {
            System.out.printf("%s\n", "User Updated successfully");
        }

    }

    //add student results
    public void registerStudentResults() {
        System.out.printf("%s\n", "Enter students details: ");
        System.out.printf("%s\n", "Enter Registration Number: ");
        regNo = input.next();
        System.out.printf("%s\n", "Enter Date: ");
        createdOn = input.next();
        System.out.printf("%s\n", "Enter Marks attained: ");
        marks_attained = input.nextInt();

        grade = grading.getGrade(marks_attained);
        status = (40 <= marks_attained) ? "Passed" : "Failed";

        int confirm = resultQueries.addResults(marks_attained, createdOn, status, regNo, grade);

        if (confirm == 1) {
            System.out.printf("%s\n", "Details saved successfully");
        }

    }

    //show results
    public void showResults() {
        System.out.printf("%s\n", "Enter student Registration Number: ");
        regNo = input.next();

        System.out.printf("%30s%30s%30s%30s%n", "Grade", "Marks Attained", "Created On", "Student Names");
        list = resultQueries.getStudentResults(regNo);//get results

        for (int index = 0; index < list.size(); index += 1) {
            StudentResult result = list.get(index);
            Student student = stdQueries.getStudentByRegNo(result.getRegNumber()).get(0);

            firstName = student.getFirstName();
            lastName = student.getLastName();
            System.out.printf("%30s%30s%30s%30s\n", result.getGrade(), result.getMarks_attained(), result.getCreated_On(), (firstName + " " + lastName));
        }

    }

}
