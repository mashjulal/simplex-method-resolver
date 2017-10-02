package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.M;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import java.util.ArrayList;
import java.util.List;


class InputData {

    private static List<List<Integer>> sEquationSystemCoefficients;
    private static List<String> sComparisonSigns;
    private static List<Integer> sEquationSystemConstants;
    private static List<Integer> sTargetFunctionCoefficients;
    private static Integer sTargetFunctionConstant;

    private static InputData ourInstance;

    static InputData createInstance(List<List<Integer>> equationSystemCoefficients,
                                    List<String> comparisonSigns,
                                    List<Integer> equationSystemConstants,
                                    List<Integer> targetFunctionCoefficients,
                                    Integer targetFunctionConstant) {
        if (ourInstance == null) {
            sEquationSystemCoefficients = equationSystemCoefficients;
            sComparisonSigns = comparisonSigns;
            sEquationSystemConstants = equationSystemConstants;
            sTargetFunctionCoefficients = targetFunctionCoefficients;
            sTargetFunctionConstant = targetFunctionConstant;
            
            ourInstance = new InputData();
        }
        return ourInstance;
    }

    private InputData() {
        // Empty private constructor of Singleton class
    }

    private List<Equation> createEquationList() {
        List<Equation> equations = new ArrayList<>();
        List<Coefficient> coefficients;
        Coefficient constant;
        for (int i = 0; i < sEquationSystemCoefficients.size(); i++) {
            coefficients = new ArrayList<>();
            for (Integer number : sEquationSystemCoefficients.get(i)) {
                coefficients.add(new Number(number));
            }
            constant = new Number(sEquationSystemConstants.get(i));
            equations.add(new Equation(coefficients, constant));
        }

        int systemSize = equations.size();
        for (int i = 0; i < sComparisonSigns.size(); i++) {
            String sign = sComparisonSigns.get(i);
            if (!sign.equals("=")) {
                for (int j = 0; j < systemSize; j++) {
                    if (j == i) {
                        equations.get(j).addCoefficient(
                                (sign.equals("<=")) ?
                                        CoefficientFactory.ONE :
                                        CoefficientFactory.MINUS_ONE);
                    } else {
                        equations.get(j).addCoefficient(
                                CoefficientFactory.ZERO);
                    }
                }
            }
            if (sign.equals(">=")) {
                for (int j = 0; j < systemSize; j++) {
                    equations.get(j).addCoefficient((j == i) ?
                            CoefficientFactory.ONE :
                            CoefficientFactory.ZERO);
                }
            }
        }
        return equations;
    }

    private static TargetFunction createTargetFunction(IsFakeVariablesList fakeVariables, boolean isMax) {
        List<Coefficient> coefficients = new ArrayList<>();
        for (int i = 0; i < fakeVariables.size(); i++) {
            if (i < sTargetFunctionCoefficients.size()) {
                coefficients.add(new Number(sTargetFunctionCoefficients.get(i)).negate());
            } else {
                coefficients.add((fakeVariables.get(i)) ?
                        CoefficientFactory.M :
                        CoefficientFactory.ZERO);
            }
        }
        TargetFunction tf = TargetFunction.createTargetFunction(coefficients,
                new Number(sTargetFunctionConstant));

        for (int i = 0; i < tf.size(); i++) {
            if (!(tf.getCoefficient(i) instanceof M) && isMax) {
                tf.setCoefficient(i , tf.getCoefficient(i).negate());
            }
        }
        if (isMax)
            tf.setValue(tf.getValue().negate());

        return tf;
    }

    public EquationSystem createEquationSystem(boolean isMax) {
        List<Equation> equations = createEquationList();
        IsFakeVariablesList fakeVariables = new IsFakeVariablesList(
                sEquationSystemCoefficients.get(0).size(), sComparisonSigns);
        TargetFunction targetFunction = TargetFunction.createTargetFunction(
                sTargetFunctionCoefficients, sTargetFunctionConstant, fakeVariables, isMax);

        return new EquationSystem(equations, fakeVariables, targetFunction);
    }
}
