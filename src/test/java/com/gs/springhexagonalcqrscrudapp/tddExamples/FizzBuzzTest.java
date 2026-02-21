package com.gs.springhexagonalcqrscrudapp.tddExamples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void shouldReturnNumberWhenNotDivisibleBy3or5(){
        assertEquals("1",fizzBuzz.convert(1));
    }

    @Test
    void shouldReturnFizzWhenDivisibleBy3(){
        assertEquals("Fizz",fizzBuzz.convert(3));
    }

    @Test
    void shouldReturnBuzzWhenDivisibleBy5(){
        assertEquals("Buzz",fizzBuzz.convert(5));
    }

    @Test
    void shouldReturnFizzBuzzWhenDivisibleBy5And3(){
        assertEquals("FizzBuzz",fizzBuzz.convert(15));
    }

    @Test
    void shouldReturn0WhenNumIsZero(){
        assertEquals("0",fizzBuzz.convert(0));
    }

    @Test
    void shouldReturn0WhenNumIsNegative(){
        assertEquals("0",fizzBuzz.convert(-10));
    }
}