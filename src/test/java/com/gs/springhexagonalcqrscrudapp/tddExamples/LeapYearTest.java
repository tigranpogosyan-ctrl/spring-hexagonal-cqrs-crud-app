package com.gs.springhexagonalcqrscrudapp.tddExamples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeapYearTest {

    private final LeapYear leapYear = new LeapYear();

    @Test
    void yearNotDivisibleBy4IsNotLeap() {
        assertFalse(leapYear.isLeap(2023));
    }

    @Test
    void yearDivisibleBy4IsLeap() {
        assertTrue(leapYear.isLeap(2024));
    }

    @Test
    void yearDivisibleBy100IsNotLeap() {
        assertFalse(leapYear.isLeap(1900));
    }

    @Test
    void yearDivisibleBy400IsLeap() {
        assertTrue(leapYear.isLeap(2000));
    }
}

