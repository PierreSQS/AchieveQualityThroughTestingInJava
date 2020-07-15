package com.openclassrooms.testing.calculator.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.openclassrooms.testing.calculator.domain.Calculator;
import com.openclassrooms.testing.calculator.domain.model.CalculationModel;

public class BatchCalculatorPierrotTest {
	
	private static final String PATH_TO_FAKE_FILE = "/path/to/fake/file";

//	private List<String> calculations;
	
	private BatchCalculator classUnderTest;
	
	@Mock
	private BatchCalculationFileService batchCalculationFileSrv;
    
	@Mock
	private Calculator calculator;
    
	@Mock
	private SolutionFormatter formatter;
	
	@Before
	public void setUp() throws IOException {
		Stream<String> calculations = Arrays.asList("3 + 4", "4 x 5", "20 / 5").stream();
		when(batchCalculationFileSrv.read(Mockito.any(String.class))).thenReturn(calculations);
		
		classUnderTest = new BatchCalculator(batchCalculationFileSrv, calculator, formatter);
		
	}
	
	@Test
	public void calculate_When_given_A_Solution() throws IOException {
		// Arrange
		
		// Act
		// classUnderTest.calculateFromFile(Mockito.anyString());
		// Assert
		
	}
	
	@Test
	public void calculateFromFile_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
		// Act
		List<CalculationModel> calculationsFromFile = classUnderTest.calculateFromFile(PATH_TO_FAKE_FILE);
		
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
