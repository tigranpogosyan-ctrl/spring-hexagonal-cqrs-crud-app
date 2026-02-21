package com.gs.springhexagonalcqrscrudapp.tddExamples;

public class LeapYear {
    public boolean isLeap(int year) {
        return (year % 400 == 0) ||
                (year % 4 == 0 && year % 100 != 0);
    }
}
