package com.gs.springhexagonalcqrscrudapp.module5CleanCode;

public class Employee {

    private String name;
    private CompensationStrategy compensationStrategy;

    public Employee(String name, CompensationStrategy compensationStrategy) {
        this.name = name;
        this.compensationStrategy = compensationStrategy;
    }

    public Money calculatePay() {
        return compensationStrategy.calculatePay(this);
    }

    public Money calculateBonus() {
        return compensationStrategy.calculateBonus(this);
    }

    public void changeCompensationStrategy(CompensationStrategy strategy) {
        this.compensationStrategy = strategy;
    }
}
