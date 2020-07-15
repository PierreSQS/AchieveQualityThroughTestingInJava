package com.openclassrooms.testing.calculator.service;

import com.openclassrooms.testing.calculator.domain.Calculator;
import com.openclassrooms.testing.calculator.domain.model.CalculationModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Supports solving calculations provided in a batch file.
 */
public class BatchCalculator implements Solver {

    // TODO Although I'm sticking to vanilla Java, the curious student,
    // can check out Project Lombok which saves you having to
    // write customer getters and setters: https://projectlombok.org/
    private BatchCalculationFileService batchCalculationFileService;
    private Calculator calculator;
    private SolutionFormatter formatter;

    /**
     * Constructor
     * @param batchCalculationFileService instance used to read the batch file
     * @param calculator instance used to solve problems
     * @param formatter the formatter of the solution (result format)
     */
    public BatchCalculator(BatchCalculationFileService batchCalculationFileService, Calculator calculator,
                           SolutionFormatter formatter) {
        this.batchCalculationFileService = batchCalculationFileService;
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public List<CalculationModel> calculateFromFile(String pathToFile) throws IOException {
        Stream<String> calculations = batchCalculationFileService.read(pathToFile);
        ArrayList<CalculationModel> solutions = new ArrayList<>();
        calculations.forEach(calculation -> {
            CalculationModel calculationModel = CalculationModel.fromText(calculation);
            CalculationModel solutionModel = solve(calculationModel, calculator, formatter);
//            solutionModel.setLeftArgument( calculationModel.getLeftArgument() );
            solutions.add(solutionModel);
        });
        return solutions;
    }
}
