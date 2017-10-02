package com.mashjulal.android.simplexmethodresolver.simplex_method;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplexMethodTest {

    private List<List<Integer>> getEquationCoefficients() {
        List<List<Integer>> equationCoefficients = new ArrayList<>();
        equationCoefficients.add(Arrays.asList(5, -3));
        equationCoefficients.add(Arrays.asList(0, 1));
        equationCoefficients.add(Arrays.asList(10, 4));

        return equationCoefficients;
    }

    private List<Integer> getEquationConstants() {
        List<Integer> equationConstants = new ArrayList<>();
        equationConstants.add(42);
        equationConstants.add(1);
        equationConstants.add(60);

        return equationConstants;
    }

    private static List<Integer> getTargetFunctionCoefficients() {
        List<Integer> coefficientList = new ArrayList<>();
        coefficientList.add(-8);
        coefficientList.add(6);
        return coefficientList;
    }

    private static Integer getTargetFunctionConstant() {
        return 0;
    }

    private static List<String> getComparisonSigns() {
        List<String> signs = new ArrayList<>();
        signs.add(Constants.ComparisonSigns.SIGN_LESS_EQUALS);
        signs.add(Constants.ComparisonSigns.SIGN_LESS_EQUALS);
        signs.add(Constants.ComparisonSigns.SIGN_BIGGER_EQUALS);
        return signs;
    }

    @Test
    public void start() throws Exception {

        List<List<Integer>> systemCoefficients = getEquationCoefficients();
        List<String> comparisonSigns = getComparisonSigns();
        List<Integer> systemConstants = getEquationConstants();

        List<Integer> functionCoefficients = getTargetFunctionCoefficients();
        Integer functionConstant = getTargetFunctionConstant();

        SimplexMethod simplexMethod = new SimplexMethod(
                systemCoefficients, comparisonSigns, systemConstants,
                functionCoefficients, functionConstant);
        simplexMethod.start();
    }

}