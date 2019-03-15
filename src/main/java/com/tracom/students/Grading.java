package com.tracom.students;

public class Grading {
    //return the grade

    public String getGrade(int marks) {
        int que = marks/10;//return quotient
        String grade = null;//return grade

        switch (que){
            case 10:
            case 9:
            case 8:
                grade = "A";//if grade is >= 80
                break;
            case 7:
            case 6:
                grade = "B";//if grade is >=60
                break;
            case 5:
            case 4:
                grade = "C";//if grade is >= 40
                break;
            default:
                grade = "D";//if  grade is < 40
        }

        return  grade;//return grade
    }
}
