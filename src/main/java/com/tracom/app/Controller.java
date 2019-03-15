package com.tracom.app;


import java.util.Scanner;

public class Controller {

    private Scanner input;
    private Service service;

    public Controller() {
        service = new Service();
        input = new Scanner(System.in);

        System.out.printf("%-1d %-20s%n",1,"Register Student");
        System.out.printf("%-1d %-20s%n",2,"Display Student");
        System.out.printf("%-1d %-20s%n",3,"Delete Student");
        System.out.printf("%-1d %-20s%n",4,"Update Student");
        System.out.printf("%-1d %-20s%n",5,"Enter results");
        System.out.printf("%-1d %-20s%n",6,"Show results");
        System.out.printf("%-1d %-20s%n",7,"Create Table");
        System.out.printf("%-1d %-20s%n",8,"Drop Table");
        System.out.printf("%s%n","Please select an action you wish to take:");

        int choice = input.nextInt();
        switch (choice){
            case 1:
                service.registerStudent();
                break;
            case 2:
                service.showStudent();
                break;
            case 3:
                service.deleteStudent();
                break;
            case 4:
                service.updateStudent();
                break;
            case 5:
                service.registerStudentResults();
                break;
            case 6:
                service.showResults();
                break;
            case 7:
                service.createTable();
                break;
            case 8:
                service.dropTable();
                break;
        }
    }
}
