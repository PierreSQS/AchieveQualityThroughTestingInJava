package com.openclassrooms.testing.calculator.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.testing.calculator.domain.Calculator;
import com.openclassrooms.testing.calculator.domain.model.CalculationModel;
import com.openclassrooms.testing.calculator.domain.model.CalculationType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CalculatorServicePierrotTest {
	
	private CalculationModel calcModel;
	
	private CalculatorService classUnderTest;
	
	@Mock
	private Calculator calc;
	
	@Mock
	private SolutionFormatter formatter;
	
	
	@Before
	public void setUp() {
		classUnderTest = new CalculatorService(calc, formatter);
	}

    @Test
	public void calculate_should_Return_the_Right_Calculation_After_Addition() {
		// Arrange
    	when(calc.add(-10, +5)).thenReturn(-5);
    	calcModel = new CalculationModel(CalculationType.ADDITION, -10, +5);
    	
    	// Act
    	CalculationModel addRes = classUnderTest.calculate(calcModel);
    	
    	// Assert
    	assertThat(addRes.getSolution(), is(equalTo(-5)));
    }
    
    @Test
    public void calculate_usesCalculatorToDivide_whenGivenADivisionCalculation() {
		// Arrange
    	when(calc.divide(-20, +5)).thenReturn(-4);
    	calcModel = new CalculationModel(CalculationType.DIVISION, -20, +5);
    	
    	// Act
    	classUnderTest.calculate(calcModel);
    	
    	// Assert
    	verify(calc, times(1)).divide(-20, +5);    	
    }
    
    @Test
    public void calculate_shouldReturnNull_ifGivenANullModel() {
    	// Arrange
    	calcModel = null;
    	
    	// Act
    	classUnderTest.calculate(calcModel);
    	
    	// Assert
    	verifyZeroInteractions(calc);;
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculate_shouldThrowAnException_whenDividingByZero() {
    	calcModel = new CalculationModel(CalculationType.DIVISION, 5, 0);
    	
    	when(calc.divide(5, 0)).thenThrow(new IllegalArgumentException("Divisor cannot be zero!"));
    	
    	classUnderTest.calculate(calcModel);
    }
}
