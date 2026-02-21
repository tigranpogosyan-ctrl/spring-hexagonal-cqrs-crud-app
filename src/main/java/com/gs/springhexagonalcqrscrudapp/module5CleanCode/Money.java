package com.gs.springhexagonalcqrscrudapp.module5CleanCode;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    @Override
    public String toString() {
        return amount.toString();
    }
}
