package com.example.productservice_proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("Testing 1+2 = 3")
    public void Test_AddTwoInteger_ReturnInteger() {
        //Arrange
        Calculator calculator = new Calculator();
        //Act
        int result = calculator.add(1, 2);
        //Assert
        assertEquals(3, result);
    }

    @Test
    @DisplayName("dividing by zero")
    public void Test_DivideByZero_ThrowException(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }

//    @Test
    void add() {
    }

//    @Test
    void subtract() {
    }

//    @Test
    void multiply() {
    }

//    @Test
    void divide() {
    }

//    @Test
    void modulo() {
    }

//    @Test
    void power() {
    }
}