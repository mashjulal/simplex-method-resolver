package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;

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
        /**
         def __init__(self, s_coeffs, c_signs, s_values, f_coeffs, f_value):
         """
         Constructor.
         :param s_coeffs: equation system variable coefficients.
         :param c_signs: equation system comparison signs.
         :param s_values: equation system right values.
         :param f_coeffs: target function variable coefficients.
         :param f_value: target function value.
         """
         self.equation_system = EquationSystem()
         SimplexMethod.create_fake_variables(s_coeffs, c_signs)
         SimplexMethod.create_equation_system(c_signs, s_coeffs, s_values)
         SimplexMethod.create_target_function(f_coeffs, f_value)
         self.basis = None
         */
        mInputData = InputData.createInstance(systemCoefficients, comparisonSigns,
                systemConstants, targetFunctionCoefficients, targetFunctionConstant);
    }

    void start() {
        /**
         * def start(self):
         """
         Starts simplex method calculations and
         prints result of every step in it.
         :return: None.
         """
         for title in ["Максимум:", "Минимум: "]:
         print(title)
         # set default values to equation system
         self.equation_system.reload_fake_values()
         self.equation_system.reload_target_function(title == "Максимум:")
         self.equation_system.reload_equations()
         # complete calculations
         self.get_solution_for_function()
         print("_"*60)
         */
        mEquationSystem = mInputData.createEquationSystem(true);
        showInitialEquationSystem();
        for (String title : Arrays.asList("Максимум:", "Минимум:")) {
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
        /**
         * ef get_estimated_attitude(self, e_index):
         """
         Returns estimated attitude for equation system.
         :param e_index: index of equation.
         :return: estimated attitude.
         """
         ea = []
         for eq in self.equation_system.get_equations():
         value, elem = eq.get_value(), eq[e_index]
         if elem <= 0 or (value < 0 < elem) or (value > 0 > elem):
         ea.append(inf)
         else:
         ea.append(value/elem)
         return ea
         */
        List<Coefficient> ea = new ArrayList<>();
        for (Equation e : mEquationSystem.getEquationList()) {
            Coefficient value = e.getValue(), elem = e.getCoefficient(equationIndex);
            if (elem.lessEquals(CoefficientFactory.getZero()) ||
                    (value.less(CoefficientFactory.getZero()) && elem.bigger(CoefficientFactory.getZero())) ||
                    (value.bigger(CoefficientFactory.getZero()) && elem.less(CoefficientFactory.getZero()))) {
                ea.add(CoefficientFactory.getInfinity());
            } else {
                ea.add(value.divide(elem));
            }
        }
        return ea;
    }

    private void getNewSystem(Equation eq, int valueIndex, int elemIndex) {
        /**
         *  def get_new_system(self, eq, val_index, elem_index):
         """
         Expresses equation system.
         :param eq: equation
         :param val_index: index of equation.
         :param elem_index: index of value.
         :return: None.
         """
         for i in range(len(self.equation_system)):
            if i != val_index:
                row = self.equation_system[i]
                multiplier = row[elem_index]
                eqq = [val * multiplier for val in eq]
                eeqq = [eqq[j]+row[j] if j != elem_index else 0 for j in range(len(eqq))]
                if i < len(self.equation_system.equations):
                    self.equation_system[i] = Equation(eeqq)
                else:
                    self.equation_system[i] = TargetFunction(eeqq)
         */
        for (int i = 0; i < mEquationSystem.size(); i++) {
            if (i != valueIndex) {
                Equation row = mEquationSystem.get(i);
                Coefficient multiplier = row.getCoefficient(elemIndex);
                Equation eqq = new Equation(new ArrayList<>(), eq.getValue().multiply(multiplier));
                for (Coefficient c : eq) {
                    eqq.addCoefficient(c.multiply(multiplier));
                }
                List<Coefficient> eeqq = new ArrayList<>();
                for (int j = 0; j < eqq.size(); j++) {
                    eeqq.add((j != elemIndex) ?
                            eqq.getCoefficient(j).add(row.getCoefficient(j)) :
                            CoefficientFactory.getZero());
                }
                Coefficient v = row.getValue().add(eqq.getValue());
                if (i < mEquationSystem.getEquationList().size()) {
                    mEquationSystem.set(i, new Equation(eeqq, v));
                } else {
                    mEquationSystem.set(i, new TargetFunction(eeqq, v));
                }
            }
        }
    }

    private void showSolution(Coefficient targetFunctionConstant, Boolean isInfinite, Boolean notOptimizable) {
        /**
         * def show_solution(self, f_v=0, is_infinite=False, not_optimizable=False):
         """
         Prints solution for equation system.
         :param f_v: target function value.
         :param is_infinite: if solution is infinite.
         :param not_optimizable: if solution can't be optimized.
         :return: None.
         """
         print("Ответ:")

         if is_infinite:
         print("Решение не может быть получено.")
         elif not_optimizable:
         print("Оптимизированного решения не существует.")
         else:
         print("\tБазис - {}".format(self.basis))
         print("\tЗначение целевой функции: {}".format(f_v))
         */
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

    private void removeFakeVariables(int firstFakeIndex) {
        /**
         *  def remove_fake_values(self, first_fake_index):
         """
         Removes values from equation system and fake
         variable list if it is fake.
         :param first_fake_index: first fake variable index.
         :return:
         """
         while True in self.equation_system.is_fake_values:
            for i in range(len(self.equation_system)):
                del self.equation_system[i][first_fake_index]
                del self.equation_system.is_fake_values[first_fake_index]
         */
        while (mEquationSystem.getIsFakeVariableList().contains(Boolean.TRUE)) {
            for (int i = 0; i < mEquationSystem.size(); i++) {
                mEquationSystem.get(i).remove(firstFakeIndex);
                mEquationSystem.getIsFakeVariableList().remove(firstFakeIndex);
            }
        }
    }

    private void getFirstSolution() {
        /**
         * def get_first_solution(self):
         """
         Complete first step of simplex method.
         :return: None.
         """
         for fake_index in [i for i, is_fake in
         enumerate(EquationSystem.INITIAL_FAKE_VARIABLES)
         if is_fake]:
         equalation_with_fake_value = [i for i in range(
         len(self.equation_system)) if self.equation_system[i]
         [fake_index] != 0][0]
         multiplier = self.equation_system.target_function[fake_index]
         eq = Equation(self.equation_system[equalation_with_fake_value])\
         .express(fake_index)
         eqq = [val * multiplier for val in eq]
         self.equation_system.target_function = \
         TargetFunction([eqq[j] +
         self.equation_system.target_function[j]
         if j != fake_index else 0
         for j in range(len(eqq))])
         */
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
                        .equals(CoefficientFactory.getZero())) {
                    equationWithFakeIndex = i;
                    break;
                }
            }
            Coefficient multiplier = mEquationSystem.getTargetFunction().getCoefficient(fakeIndex);
            Equation eq = mEquationSystem.get(equationWithFakeIndex).express(fakeIndex);
            Equation eqq = eq.multiply(multiplier);
            TargetFunction tf = new TargetFunction(
                    new ArrayList<>(),
                    eqq.getValue().add(mEquationSystem.getTargetFunction().getValue()));
            for (int j = 0; j < eqq.size(); j++) {
                tf.addCoefficient((j == fakeIndex) ?
                        eqq.getCoefficient(j).add(mEquationSystem.getTargetFunction().getCoefficient(j)):
                        CoefficientFactory.getZero());
            }

            mEquationSystem.setTargetFunction(tf);
        }


    }

    private void getSolution() {
        /**
         * def get_solution_for_function(self):
         """
         Perform simplex method.
         :return: None.
         """
         fake_variables = copy.deepcopy(EquationSystem.INITIAL_FAKE_VARIABLES)
         self.basis = Basis(self.equation_system.get_equations())

         if any(is_fake for is_fake in fake_variables):
            print("Первое решение:")
            self.get_first_solution()
            print("Новая симплекс-таблица")
            print(self.equation_system)

            self.basis.recalculate(self.equation_system)
            print("Базис - {}".format(self.basis))

            print("Значение целевой функции: {}"
            .format(self.equation_system.target_function.get_value()))
            print("_"*60)

         while min(self.equation_system.target_function.get_coefficients()) < 0:
         min_elem = min([v for v in self.equation_system.target_function
         .get_coefficients()
         if v < 0])
         min_elem_index = self.equation_system.target_function\
         .index(min_elem)

         och_otn = self.get_estimated_attitude(min_elem_index)
         eq_index = och_otn.index(min(och_otn))

         print("Оценочное отношение x{}: {}"
         .format(min_elem_index+1, [str(v) for v in och_otn]))

         if min(och_otn) == inf:
         self.show_solution(is_infinite=True)
         return

         eq = self.equation_system[eq_index].express(min_elem_index)
         print("Коэффициенты выраженного {} уравнения - {}"
         .format(eq_index+1, [str(v) for v in och_otn]))

         self.get_new_system(eq, eq_index, min_elem_index)
         print("Новая симплекс-таблица")
         print(self.equation_system)

         self.basis.replace_value(
         eq_index,
         min_elem_index,
         self.equation_system[eq_index][-1]
         / self.equation_system[eq_index][min_elem_index])
         self.basis.recalculate(self.equation_system)
         print("Базис - {}".format(self.basis))

         print("Значение целевой функции: {}"
         .format(self.equation_system.target_function.get_value()))
         print("_"*60)

         any_fake = True in fake_variables
         fake_not_in_basis = all(
         [not(is_fake and i in self.basis.get_indexes())
         for i, is_fake in enumerate(fake_variables)])

         if any_fake and fake_not_in_basis:
         self.remove_fake_values(fake_variables.index(True))

         if any(is_fake for is_fake in self.equation_system.is_fake_values):
         self.show_solution(not_optimizable=True)
         return

         self.show_solution([-1, 1]
         [self.equation_system.target_function[-1] > 0] *
         self.equation_system.target_function[-1])
         */
        List<Boolean> fakeVariables = mEquationSystem.getIsFakeVariableList();
        mBasis = new Basis(mEquationSystem.getEquationList());

        if (fakeVariables.stream().anyMatch((isFake) -> isFake)) {
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

        while (mEquationSystem.getTargetFunction().getCoefficients().stream()
                .min(Coefficient::compareTo).get().bigger(CoefficientFactory.getZero())) {
            Coefficient minElem = mEquationSystem.getTargetFunction().getCoefficients().stream()
                    .min(Coefficient::compareTo).get();
            int minElemIndex = mEquationSystem.getTargetFunction().index(minElem);

            List<Coefficient> estAtt = getEstimatedAttitude(minElemIndex);
            int eqIndex = estAtt.indexOf(estAtt.stream().min(Coefficient::compareTo).get());
            System.out.println(String.format(Locale.getDefault(), "Оценочное отношение x%d: %s",
                    minElemIndex + 1, estAtt.toString()));

            if (estAtt.stream().min(Coefficient::compareTo)
                    .get().equals(CoefficientFactory.getInfinity())) {
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
            Boolean fakeNotInBasis = fakes.stream().allMatch((isFake) -> isFake);
            if (anyFake && fakeNotInBasis) {
                removeFakeVariables(fakeVariables.indexOf(Boolean.TRUE));
            }

            if (fakeVariables.contains(Boolean.TRUE)) {
                showSolution(null, false, true);
                return;
            }

            Coefficient targetFunctionConstant =
                    (mEquationSystem.getTargetFunction().getValue()
                            .bigger(CoefficientFactory.getZero())) ?
                            mEquationSystem.getTargetFunction().getValue() :
                            mEquationSystem.getTargetFunction().getValue().negate();
            showSolution(targetFunctionConstant, false, false);
        }
    }
}
