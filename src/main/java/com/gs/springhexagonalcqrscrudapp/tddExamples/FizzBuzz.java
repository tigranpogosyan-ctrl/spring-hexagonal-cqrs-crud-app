package com.gs.springhexagonalcqrscrudapp.tddExamples;

public class FizzBuzz {
    public String convert(int num) {
        if (num < 0 || num == 0){
            return String.valueOf(0);
        }
        if (num % 3 == 0 && num % 5 == 0) {
            return "FizzBuzz";
        } else if (num % 3 == 0) {
            return "Fizz";
        } else if (num % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(num);
    }
}
