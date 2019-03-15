package com.tracom.results;

public class StudentResult {
    private int id;
    private int marks_attained;
    private String created_On;
    private String status;
    private String regNumber;
    private String grade;

    //constructor
    public StudentResult(int id, int marks_attained, String created_On, String status, String regNumber,String grade) {
        setId(id);
        setMarks_attained(marks_attained);
        setCreated_On(created_On);
        setStatus(status);
        setRegNumber(regNumber);
        setGrade(grade);
    }
    //return id
    public int getId() {
        return id;
    }

    //setId
    public void setId(int id) {
        this.id = id;
    }

    //return marks_attained
    public int getMarks_attained() {
        return marks_attained;
    }

    //set marks_attained
    public void setMarks_attained(int marks_attained) {
        this.marks_attained = marks_attained;
    }

    //return created_On
    public String getCreated_On() {
        return created_On;
    }

    //set created_on
    public void setCreated_On(String created_On) {
        this.created_On = created_On;
    }

    //get status
    public String getStatus() {
        return status;
    }

    //set status
    public void setStatus(String status) {
        this.status = status;
    }

    //return Reg number
    public String getRegNumber() {
        return regNumber;
    }

    //set RegNo
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    //get grade
    public String getGrade() {
        return grade;
    }

    //set grade
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
