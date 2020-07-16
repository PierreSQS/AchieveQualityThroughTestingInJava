package com.openclassrooms.testing.calculator.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.testing.calculator.domain.Calculator;
import com.openclassrooms.testing.calculator.domain.model.CalculationModel;

@RunWith(MockitoJUnitRunner.class)
public class BatchCalculatorPierrotTest {
	
	private static final String PATH_TO_FAKE_FILE = "/path/to/fake/file";

	private Stream<String> calculations;
	
	private BatchCalculator classUnderTest;
	
	@Mock
	private BatchCalculationFileService batchCalculationFileSrv;
    
	@Mock
	private Calculator calculator;
    
	@Mock
	private SolutionFormatter formatter;
	
	@Before // Arrange
	public void setUp() throws IOException {
		calculations = Arrays.asList("2 * 4", "-50 + 4", "8 / 4", "19 + 11").stream();
		when(batchCalculationFileSrv.read(Mockito.any(String.class))).thenReturn(calculations);
		
		// Setup the calculator
		when(calculator.add(-50, 4)).thenReturn(-46);
		when(calculator.add(19, 11)).thenReturn(30);
		when(calculator.multiply(2, 4)).thenReturn(8);
		
		classUnderTest = new BatchCalculator(batchCalculationFileSrv, calculator, formatter);
		
	}
	
	@Test
	public void calculateFromFile_shouldReturnTheCorrectAnswer_forAdditions() throws IOException {
		// Act
		when(batchCalculationFileSrv.read(PATH_TO_FAKE_FILE)).thenReturn(calculations);
		List<CalculationModel> calculationsFromFile = classUnderTest.calculateFromFile(PATH_TO_FAKE_FILE);

		// Assert
		assertThat(calculationsFromFile.get(1).getSolution(), is(equalTo(-46)));
		assertThat(calculationsFromFile.get(3).getSolution(), is(equalTo(30)));

	}
	
	@Test
	public void calculateFromFile_shouldReturnThreeSolutions_forThreeCalculations() throws IOException {
		
		when(batchCalculationFileSrv.read(PATH_TO_FAKE_FILE)).thenReturn(calculations);
		
		List<CalculationModel> calculationsFromFile = classUnderTest.calculateFromFile(PATH_TO_FAKE_FILE);
		
		assertThat(calculationsFromFile, hasSize(4));
	}
	
	@Test
	public void calculateFromFile_shouldCorrectlyMultiplyWithTheCalculator_forProducts() throws IOException {
		// Act
		List<CalculationModel> calculationsFromFile = classUnderTest.calculateFromFile(PATH_TO_FAKE_FILE);
		
		// Assert
		assertThat(calculationsFromFile.get(0).getSolution(), is(equalTo(8)));
		
	}
	
	@Test
	public void calculateFromFile_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
		// Act
		classUnderTest.calculateFromFile(PATH_TO_FAKE_FILE);
		
		// Assert 
		// there for we verify that read Method of the BatchCalculatorService is called
		verify(batchCalculationFileSrv).read(PATH_TO_FAKE_FILE);
	}
	
	@Test // not to recommend
	public void a_test_by_calling_the_calculate_Method() throws IOException {
		BatchCalculationFileService batchServ = file -> Files.lines(Paths.get(file));
		Calculator calc = new Calculator();
		SolutionFormatter formatter = solution -> String.format("%,d", solution);
		
		BatchCalculator batchCalc = new BatchCalculator(batchServ, calc, formatter);
		List<CalculationModel> calculationsFromFile = batchCalc.calculateFromFile("C:\\Pierrot\\05_Git_Repositories\\achieve-quality-through-testing-github-pierrot\\src\\test\\resources\\data\\calculations");
//		List<CalculationModel> calculationsFromFile = batchCalc.calculateFromFile("data/calculations");
		calculationsFromFile.forEach(calculation -> System.out.println(calculation.toString()));
	}
	


}
