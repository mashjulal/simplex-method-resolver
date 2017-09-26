package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;
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
        if (ourInstance != null) {
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
            sEquationSystemCoefficients.get(i).forEach((n) -> coefficients.add(new Number(n)));
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
                                        CoefficientFactory.getOne() :
                                        CoefficientFactory.getMinusOne());
                    } else {
                        equations.get(j).addCoefficient(
                                CoefficientFactory.getZero());
                    }
                }
            }
            if (sign.equals(">=")) {
                for (int j = 0; j < systemSize; j++) {
                    equations.get(j).addCoefficient((j == i) ?
                            CoefficientFactory.getOne() :
                            CoefficientFactory.getZero());
                }
            }
        }
        return equations;
    }

    private static List<Boolean> createFakeVariables() {
        /**
         *  @staticmethod
        def create_fake_variables(s_c, c_s):
        """
        Creates fake variable list from parameters.
        :param s_c: equation system variable coefficients.
        :param c_s: equation system comparison signs.
        :return: None.
        """
        EquationSystem.INITIAL_FAKE_VARIABLES = [False for _ in s_c[0]] + \
        [False for sign in c_s if
        sign != "="] + \
        [True for sign in c_s if
        sign == ">="] + \
        [False]
         */
        List<Boolean> fv1 = new ArrayList<>();
        for (int i = 0; i < sEquationSystemCoefficients.get(0).size(); i++) {
            fv1.add(Boolean.FALSE);
        }

        List<Boolean> fv2 = new ArrayList<>();
        for (String sign : sComparisonSigns) {
            if (!sign.equals("="))
                fv2.add(Boolean.FALSE);
        }
        List<Boolean> fv3 = new ArrayList<>();
        for (String sign : sComparisonSigns) {
            if (sign.equals(">=")) {
                fv3.add(Boolean.TRUE);
            }
        }
        Boolean fv4 = Boolean.FALSE;

        List<Boolean> fakeVariables = new ArrayList<>();
        fakeVariables.addAll(fv1);
        fakeVariables.addAll(fv2);
        fakeVariables.addAll(fv3);
        fakeVariables.add(fv4);

        return fakeVariables;
    }

    private static TargetFunction createTargetFunction(List<Boolean> fakeVariables, boolean isMax) {
        /**
         *  @staticmethod
        def create_target_function(f_c, f_v):
        """
        Creates target function from parameters.
        :param f_c: target function variable coefficients.
        :param f_v: target function value.
        :return: None.
        """
        target_function = TargetFunction([])
        for i in range(len(EquationSystem.INITIAL_FAKE_VARIABLES) - 1):
        if i < len(f_c):
        target_function.add_coefficient(-Fraction(f_c[i]))
        else:
        target_function.add_coefficient(
        [Fraction(0), M(1, 0)]
        [EquationSystem.INITIAL_FAKE_VARIABLES[i]])
        target_function.add_coefficient(f_v)
        EquationSystem.INITIAL_TARGET_FUNCTION = target_function
         */

        List<Coefficient> coefficients = new ArrayList<>();
        for (int i = 0; i < fakeVariables.size(); i++) {
            if (i < sTargetFunctionCoefficients.size()) {
                coefficients.add(new Number(sTargetFunctionCoefficients.get(i)).negate());
            } else {
                coefficients.add((fakeVariables.get(i)) ? (isMax) ?
                        CoefficientFactory.getMinusOneM() :
                        CoefficientFactory.getOneM() :
                        CoefficientFactory.getZero());
            }
        }
        return new TargetFunction(coefficients,
                new Number(sTargetFunctionConstant));
    }

    public EquationSystem createEquationSystem(boolean isMax) {
        List<Equation> equations = createEquationList();
        List<Boolean> fakeVariables = createFakeVariables();
        TargetFunction targetFunction = createTargetFunction(fakeVariables, isMax);

        return new EquationSystem(equations, fakeVariables, targetFunction);
    }
}
