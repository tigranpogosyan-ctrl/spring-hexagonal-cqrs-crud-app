package com.gs.springhexagonalcqrscrudapp.module5CleanCode;


public class InvalidEmployeeType extends Exception {

    public InvalidEmployeeType(EmployeeType type) {
        super("Invalid employee type: " + type);
    }
}
