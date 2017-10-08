package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class SimplexMethod {

    private InputData mInputData;
    private EquationSystem mEquationSystem;
    private Basis mBasis;

    SimplexMethod(List<List<Integer>> systemCoefficients, List<String> comparisonSigns,
                  List<Integer> systemConstants, List<Integer> targetFunctionCoefficients,
                  Integer targetFunctionConstant) {
        mInputData = InputData.createInstance(systemCoefficients, comparisonSigns,
                systemConstants, targetFunctionCoefficients, targetFunctionConstant);
    }

    void start() {
        mEquationSystem = mInputData.createEquationSystem(false);
        showInitialEquationSystem();
        for (String title : Arrays.asList("Минимум:", "Максимум:")) {
            System.out.println(title);
            mEquationSystem = mInputData.createEquationSystem(title.equals("Максимум:"));
            getSolution();
            System.out.println("__________________________");
        }
    }

    private void showInitialEquationSystem() {
        System.out.println("Изначальная система:");
        System.out.println(mEquationSystem);
        System.out.println("_______________________");
    }

    private List<Coefficient> getEstimatedAttitude(int equationIndex) {
        List<Coefficient> ea = new ArrayList<>();
        for (Equation e : mEquationSystem.getEquationList()) {
            Coefficient value = e.getValue(), elem = e.getCoefficient(equationIndex);
            if (elem.lessEquals(Constants.Coefficients.ZERO) ||
                    (value.less(Constants.Coefficients.ZERO) && elem.bigger(Constants.Coefficients.ZERO)) ||
                    (value.bigger(Constants.Coefficients.ZERO) && elem.less(Constants.Coefficients.ZERO))) {
                ea.add(Constants.Coefficients.INFINITY);
            } else {
                ea.add(value.divide(elem));
            }
        }
        return ea;
    }

    private void getNewSystem(Equation eq, int valueIndex, int elemIndex) {
        for (int i = 0; i < mEquationSystem.size(); i++) {
            if (i != valueIndex) {
                Equation row = mEquationSystem.get(i);
                Coefficient multiplier = row.getCoefficient(elemIndex);
                Equation eqq = new Equation(new ArrayList<Coefficient>(), eq.getValue().multiply(multiplier));
                for (Coefficient c : eq) {
                    eqq.addCoefficient(c.multiply(multiplier));
                }
                List<Coefficient> eeqq = new ArrayList<>();
                for (int j = 0; j < eqq.size(); j++) {
                    eeqq.add((j != elemIndex) ?
                            eqq.getCoefficient(j).add(row.getCoefficient(j)) :
                            Constants.Coefficients.ZERO);
                }
                Coefficient v = row.getValue().add(eqq.getValue());
                if (i < mEquationSystem.getEquationList().size()) {
                    mEquationSystem.set(i, new Equation(eeqq, v));
                } else {
                    mEquationSystem.set(i, TargetFunction.createTargetFunction(eeqq, v));
                }
            }
        }
    }

    private void showSolution(Coefficient targetFunctionConstant, Boolean isInfinite, Boolean notOptimizable) {
        System.out.println("Ответ:");

        if (isInfinite) {
            System.out.println("Решение не может быть получено.");
        } else if (notOptimizable) {
            System.out.println("Оптимизированного решения не существует.");
        } else {
            System.out.println(String.format("\tБазис - %s",
                    mBasis.toString()));
            System.out.println(String.format("\tЗначение целевой функции: %s",
                    targetFunctionConstant.toString()));
        }

    }

    private void getFirstSolution() {
        List<Integer> fakeIndexes = new ArrayList<>();
        for (int i = 0; i < mEquationSystem.getIsFakeVariableList().size(); i++) {
            if (mEquationSystem.getIsFakeVariableList().get(i)) {
                fakeIndexes.add(i);
            }
        }
        for (Integer fakeIndex : fakeIndexes) {
            Integer equationWithFakeIndex = -1;
            for (int i = 0; i < mEquationSystem.size(); i++) {
                if (!mEquationSystem.get(i).getCoefficient(fakeIndex)
                        .equals(Constants.Coefficients.ZERO)) {
                    equationWithFakeIndex = i;
                    break;
                }
            }
            Coefficient multiplier = mEquationSystem.getTargetFunction().getCoefficient(fakeIndex);
            Equation eq = mEquationSystem.get(equationWithFakeIndex).express(fakeIndex);
            Equation eqq = eq.multiply(multiplier);

            List<Coefficient> tfCof = new ArrayList<>();
            for (int j = 0; j < eqq.size(); j++) {
                tfCof.add((j != fakeIndex) ?
                        eqq.getCoefficient(j).add(mEquationSystem.getTargetFunction().getCoefficient(j)):
                        Constants.Coefficients.ZERO);
            }
            TargetFunction tf = TargetFunction.createTargetFunction(
                    tfCof,
                    eqq.getValue().add(mEquationSystem.getTargetFunction().getValue()));
            mEquationSystem.setTargetFunction(tf);
        }


    }

    private void getSolution() {
        IsFakeVariablesList fakeVariables = mEquationSystem.getIsFakeVariableList();
        mBasis = new Basis(mEquationSystem.getEquationList());

        if (fakeVariables.contains(Boolean.TRUE)) {
            System.out.println("Первое решение:");
            getFirstSolution();
            System.out.println("Новая симплекс-таблица");
            System.out.println(mEquationSystem.toString());

            mBasis.recalculate(mEquationSystem.getEquationList());
            System.out.println(String.format("Базис - %s", mBasis.toString()));

            System.out.println(String.format("Значение целевой функции: %s",
                    mEquationSystem.getTargetFunction().getValue()));
            System.out.println("__________________");
        }

        Coefficient minElem = mEquationSystem.getTargetFunction().getCoefficients().get(0);

        for (Coefficient c : mEquationSystem.getTargetFunction().getCoefficients())
            if (minElem.bigger(c))
                minElem = c;
        while (minElem.less(Constants.Coefficients.ZERO)) {
            int minElemIndex = mEquationSystem.getTargetFunction().index(minElem);

            List<Coefficient> estAtt = getEstimatedAttitude(minElemIndex);

            Coefficient minEstAtt = estAtt.get(0);
            for (Coefficient c : estAtt)
                if (minEstAtt.bigger(c))
                    minEstAtt = c;
            int eqIndex = estAtt.indexOf(minEstAtt);
            System.out.println(String.format(Locale.getDefault(), "Оценочное отношение x%d: %s",
                    minElemIndex + 1, estAtt.toString()));

            if (minEstAtt.equals(Constants.Coefficients.INFINITY)) {
                showSolution(null, true, null);
                return;
            }
            Equation eq = mEquationSystem.getEquationList().get(eqIndex).express(minElemIndex);
            System.out.println(String.format(Locale.getDefault(),
                    "Коэффициенты выраженного %d уравнения - %s",
                    eqIndex + 1, estAtt.toString()));

            getNewSystem(eq, eqIndex, minElemIndex);
            System.out.println("Новая симплекс-таблица");
            System.out.println(mEquationSystem.toString());

            mBasis.setBasisValue(eqIndex, new BasisValue(minElemIndex,
                    mEquationSystem.getEquationList().get(eqIndex).getValue()
                            .divide(mEquationSystem.getEquationList().get(eqIndex)
                                    .getCoefficient(minElemIndex))));
            mBasis.recalculate(mEquationSystem.getEquationList());
            System.out.println(String.format("Базис - %s", mBasis.toString()));

            System.out.println(String.format("Значение целевой функции: %s",
                    mEquationSystem.getTargetFunction().getValue()));
            System.out.println("__________________");

            Boolean anyFake = fakeVariables.contains(Boolean.TRUE);

            List<Boolean> fakes = new ArrayList<>();
            for (int i = 0; i < fakeVariables.size(); i++) {
                Boolean isFake = fakeVariables.get(i);
                fakes.add(!(isFake && mBasis.getIndexes().contains(i)));
            }
            Boolean fakeNotInBasis = Boolean.TRUE;
            for (Boolean isFake : fakes)
                if (!isFake) {
                    fakeNotInBasis = Boolean.FALSE;
                    break;
                }
            if (anyFake && fakeNotInBasis) {
                mEquationSystem.removeFakeVariables(fakeVariables.indexOf(Boolean.TRUE));
            }

            for (Coefficient c : mEquationSystem.getTargetFunction().getCoefficients())
                if (minElem.bigger(c))
                    minElem = c;
        }

        if (fakeVariables.contains(Boolean.TRUE)) {
            showSolution(null, false, true);
            return;
        }

        Coefficient targetFunctionConstant =
                (mEquationSystem.getTargetFunction().getValue()
                        .bigger(Constants.Coefficients.ZERO)) ?
                        mEquationSystem.getTargetFunction().getValue() :
                        mEquationSystem.getTargetFunction().getValue().negate();
        showSolution(targetFunctionConstant, false, false);
    }
}
