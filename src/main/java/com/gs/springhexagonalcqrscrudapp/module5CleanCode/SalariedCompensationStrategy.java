package com.gs.springhexagonalcqrscrudapp.module5CleanCode;

import java.math.BigDecimal;

public class SalariedCompensationStrategy implements CompensationStrategy {

    @Override
    public Money calculatePay(Employee employee) {
        return new Money(BigDecimal.valueOf(4000));
    }

    @Override
    public Money calculateBonus(Employee employee) {
        return new Money(BigDecimal.valueOf(800));
    }
}
