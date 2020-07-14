package com.openclassrooms.testing.calculator.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {

    private static Instant startTime;
    private Calculator calculator;

    @BeforeClass
    public static void beforeClass() {
        startTime = Instant.now();
    }

    @AfterClass
    public static void afterClass() {
        Instant endedAt = Instant.now();
        Duration duration = Duration.between(startTime, endedAt);
    }

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    // ADDITION
    @Test
    public void add_shouldReturnTheSum_ofTwoPositiveNumbers() {
        // arrange
        Integer expected = 3; // 1+2

        // act
        Integer result = calculator.add(1, 2);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void add_shouldReturnTheSum_ofNegativeNumbers() {
        // arrange
        Integer expected = -3; // -1+-2

        // act
        Integer result = calculator.add(-1, -2);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void add_shouldReturnTheSum_ofAPositiveAndNegativeNumber() {
        // arrange
        Integer expected = 1; // -2+3

        // act
        Integer result = calculator.add(-2, 3);

        // assert
        assertEquals(expected, result);
    }

    // MULTIPLICATION
    @Test
    public void multiply_shouldReturnTheProduct_ofTwoPositiveNumbers() {
        // arrange
        Integer expected = 6;

        // act
        Integer product = calculator.multiply(2, 3);

        // assert that product is equal to 6
        assertThat(product, is(equalTo(expected))); // 2x3
    }

    // DIVISION
    @Test
    public void divide_shouldReturnTheQuotient_ofTwoPositiveNumbers() {
        Integer quotient = calculator.divide(4, 2);
        assertThat(quotient, is(equalTo(2)));
    }

    @Test
    public void divide_shouldReturnTheQuotient_ofTwoNegativeNumbers() {
        Integer quotient = calculator.divide(-4, -2);
        assertThat(quotient, is(equalTo(2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void divide_shouldThrowAnException_ifDividingByZero() {
        calculator.divide(4, 0);
    }

    // TRIG
    @Test(expected = UnsupportedOperationException.class)
    public void cos_shouldNotBeSupported_whenCalledWithAnyValue() {
        // arrange is done in @Before
        // act
        calculator.cos(0.8);
        // assertion happens in the @Test
    }

    // Slow and Unpredictable Tests
    @Test(timeout = 0020)
    public void slowCalculation_shouldTakeUnreasonablyLong_whenCalled() {
        // Act by calling a slow calculation
        calculator.slowCalculation();
    }

    // Part 2 - Chapter 2 - Screencast 2
    private static Integer lastNumber = 2;

    @Test
    @Ignore("Re-enable to make this test brittle")
    public void add_shouldAddTwo_toTwo() {
        lastNumber = calculator.add(lastNumber, 2);
        assertThat(lastNumber, is(equalTo(4)));
    }

    @Test
    @Ignore("Re-enable to make this test brittle")
    public void add_shouldAddTwo_toFour() {
        lastNumber = calculator.add(lastNumber, 2);
        assertThat(lastNumber, is(equalTo(6)));
    }

}
