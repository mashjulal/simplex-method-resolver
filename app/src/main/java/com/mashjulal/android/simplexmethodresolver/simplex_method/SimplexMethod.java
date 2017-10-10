package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import lombok.Setter;
import lombok.experimental.Accessors;

import static java.lang.String.format;

public class SimplexMethod {

    private InputData mInputData;
    private EquationSystem mEquationSystem;
    private Basis mBasis;
    @Setter @Accessors(prefix = "m") private OnResultListener mOnResultListener;
    private String mSolution;

    public SimplexMethod(List<List<Integer>> systemCoefficients, List<String> comparisonSigns,
                  List<Integer> systemConstants, List<Integer> targetFunctionCoefficients,
                  Integer targetFunctionConstant) {
        mInputData = InputData.createInstance(systemCoefficients, comparisonSigns,
                systemConstants, targetFunctionCoefficients, targetFunctionConstant);
    }

    public void start() {
        mEquationSystem = mInputData.createEquationSystem(false);
        showInitialEquationSystem();
        for (String title : Arrays.asList("Минимум:", "Максимум:")) {
            mSolution = title +"\n";
            mEquationSystem = mInputData.createEquationSystem(title.equals("Максимум:"));
            getSolution();
        }
    }

    private void showInitialEquationSystem() {
        mSolution += "Изначальная система:";;
        mSolution += mEquationSystem;;
        mSolution += "_______________________";;
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
        mSolution += "Ответ:";;

        if (isInfinite) {
            mSolution += "Решение не может быть получено.";;
        } else if (notOptimizable) {
            mSolution += "Оптимизированного решения не существует.";;
        } else {
            mSolution += format("\tБазис - %s",
                    mBasis.toString());
            mSolution += format("\tЗначение целевой функции: %s",
                    targetFunctionConstant.toString());
        }
        mOnResultListener.onResultReceived(mSolution);
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
            mSolution += "Первое решение:";
            getFirstSolution();
            mSolution += "Новая симплекс-таблица";
            mSolution += mEquationSystem.toString();

            mBasis.recalculate(mEquationSystem.getEquationList());
            mSolution += format("Базис - %s", mBasis.toString());

            mSolution += format("Значение целевой функции: %s",
                    mEquationSystem.getTargetFunction().getValue());
            mSolution += "__________________";
        }

        Coefficient minElem = mEquationSystem.getTargetFunction().min();
        while (minElem.less(Constants.Coefficients.ZERO)) {
            int minElemIndex = mEquationSystem.getTargetFunction().index(minElem);

            EstimatedAttitude ea = new EstimatedAttitude(
                    mEquationSystem.getEquationList(), minElemIndex);

            mSolution += String.format(Locale.getDefault(), "Оценочное отношение x%d: %s",
                    minElemIndex + 1, ea.toString());

            Coefficient minEstAtt = ea.min();
            int eqIndex = ea.indexOf(minEstAtt);
            if (minEstAtt.equals(Constants.Coefficients.INFINITY)) {
                showSolution(null, true, null);
                return;
            }
            Equation eq = mEquationSystem.getEquationList().get(eqIndex).express(minElemIndex);
            mSolution += String.format(Locale.getDefault(),
                    "Коэффициенты выраженного %d уравнения - %s",
                    eqIndex + 1, ea.toString());

            getNewSystem(eq, eqIndex, minElemIndex);
            mSolution += "Новая симплекс-таблица";
            mSolution += mEquationSystem.toString();

            mBasis.setBasisValue(eqIndex, new BasisValue(minElemIndex,
                    mEquationSystem.getEquationList().get(eqIndex).getValue()
                            .divide(mEquationSystem.getEquationList().get(eqIndex)
                                    .getCoefficient(minElemIndex))));
            mBasis.recalculate(mEquationSystem.getEquationList());
            mSolution += String.format("Базис - %s", mBasis.toString());

            mSolution += String.format("Значение целевой функции: %s",
                    mEquationSystem.getTargetFunction().getValue());
            mSolution += "__________________";

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
            minElem = mEquationSystem.getTargetFunction().min();
        }

        if (fakeVariables.contains(Boolean.TRUE)) {
            showSolution(null, false, true);
            return;
        }

        Coefficient targetFunctionConstant =
                (mEquationSystem.getTargetFunction().getValue().bigger(Constants.Coefficients.ZERO)) ?
                        mEquationSystem.getTargetFunction().getValue() :
                        mEquationSystem.getTargetFunction().getValue().negate();
        showSolution(targetFunctionConstant, false, false);
    }

    public interface OnResultListener {
        void onResultReceived(String solution);
    }
}
