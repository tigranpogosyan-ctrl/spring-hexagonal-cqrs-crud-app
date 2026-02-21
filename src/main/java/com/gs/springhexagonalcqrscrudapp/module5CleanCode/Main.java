package com.gs.springhexagonalcqrscrudapp.module5CleanCode;

public class Main {
    public static void main(String[] args) {
        Employee john = new Employee(
                "John",
                new CommissionCompensationStrategy()
        );

        System.out.println(john.calculatePay());
        System.out.println(john.calculateBonus());

        john.changeCompensationStrategy(new HourlyCompensationStrategy());

        System.out.println(john.calculatePay());
    }
}
