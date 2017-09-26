package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by master on 26.09.17.
 */
public class EquationSystemTest {

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
        signs.add("<=");
        signs.add("<=");
        signs.add(">=");
        return signs;
    }

    @Test
    public void toString_isCorrect() throws Exception {
        EquationSystem equationSystem = InputData.createInstance(
                getEquationCoefficients(),
                getComparisonSigns(),
                getEquationConstants(),
                getTargetFunctionCoefficients(),
                getTargetFunctionConstant()).createEquationSystem(true);

        String rep = "";

        assertEquals(rep, equationSystem.toString());
    }

    @Test
    public void size_isCorrect() throws Exception {
        EquationSystem equationSystem = InputData.createInstance(
                getEquationCoefficients(),
                getComparisonSigns(),
                getEquationConstants(),
                getTargetFunctionCoefficients(),
                getTargetFunctionConstant()).createEquationSystem(true);

        assertEquals(4, equationSystem.size());
    }

    @Test
    public void get_isCorrect() throws Exception {
        EquationSystem equationSystem = InputData.createInstance(
                getEquationCoefficients(),
                getComparisonSigns(),
                getEquationConstants(),
                getTargetFunctionCoefficients(),
                getTargetFunctionConstant()).createEquationSystem(true);

        assertEquals(new Equation(Arrays.asList(
                new Number(5), new Number(-3), CoefficientFactory.getZero(),
                CoefficientFactory.getZero(), CoefficientFactory.getZero(),
                CoefficientFactory.getZero()), new Number(42)), equationSystem.get(0));
    }

    @Test
    public void set_isCorrect() throws Exception {
        EquationSystem equationSystem = InputData.createInstance(
                getEquationCoefficients(),
                getComparisonSigns(),
                getEquationConstants(),
                getTargetFunctionCoefficients(),
                getTargetFunctionConstant()).createEquationSystem(true);

        equationSystem.set(0, new Equation(Arrays.asList(
                new Number(4), new Number(3), CoefficientFactory.getZero(),
                CoefficientFactory.getZero(), CoefficientFactory.getZero(),
                CoefficientFactory.getOne()), new Number(52)));

        assertEquals(new Equation(Arrays.asList(
                new Number(4), new Number(3), CoefficientFactory.getZero(),
                CoefficientFactory.getZero(), CoefficientFactory.getZero(),
                CoefficientFactory.getOne()), new Number(52)), equationSystem.get(0));
    }

    @Test
    public void add_isCorrect() throws Exception {
        EquationSystem equationSystem = InputData.createInstance(
                getEquationCoefficients(),
                getComparisonSigns(),
                getEquationConstants(),
                getTargetFunctionCoefficients(),
                getTargetFunctionConstant()).createEquationSystem(true);

        equationSystem.add(new Equation(Arrays.asList(
                new Number(5), new Number(-3), CoefficientFactory.getZero(),
                CoefficientFactory.getZero(), CoefficientFactory.getZero(),
                CoefficientFactory.getZero()), new Number(42)));

        assertEquals(new Equation(Arrays.asList(
                new Number(5), new Number(-3), CoefficientFactory.getZero(),
                CoefficientFactory.getZero(), CoefficientFactory.getZero(),
                CoefficientFactory.getZero()), new Number(42)), equationSystem.get(3));
    }

}