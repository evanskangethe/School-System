package com.tracom.students;

//Student class
public class Student {
    private String  id;
    private  String firstName;
    private String lastName;
    private String nationalId;
    private String regNumber;
    private String createdOn;

    //Constructor
    public Student(String id, String firstName, String lastName, String nationalId, String regNumber, String createdOn) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setNationalId(nationalId);
        setRegNumber(regNumber);
        setCreatedOn(createdOn);
    }

    //return ID
    public String getId() {
        return id;
    }

    //set ID
    public void setId(String id) {
        this.id = id;
    }

    //return first name
    public String getFirstName() {
        return firstName;
    }

    //set first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //return last name
    public String getLastName() {
        return lastName;
    }

    //set last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //return national ID
    public String getNationalId() {
        return nationalId;
    }

    //set national ID
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    //return reg Number
    public String getRegNumber() {
        return regNumber;
    }

    //set reg Number
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    //return createdOn
    public String getCreatedOn() {
        return createdOn;
    }

    //set createdOn
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
