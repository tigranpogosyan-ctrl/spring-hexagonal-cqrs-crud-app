package com.gs.springhexagonalcqrscrudapp.module5CleanCode;

public interface CompensationStrategy {

    Money calculatePay(Employee employee);

    Money calculateBonus(Employee employee);
}
