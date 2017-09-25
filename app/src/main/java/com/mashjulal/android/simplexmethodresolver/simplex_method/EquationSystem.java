package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;


class EquationSystem implements Iterable<Equation> {

    /**
     *  # Static list for storing initial equation list
     INITIAL_EQUATIONS = []
     # Static TargetFunction for storing initial target function
     INITIAL_TARGET_FUNCTION = TargetFunction([])
     # Static list for storing initial list of fake variables states
     INITIAL_FAKE_VARIABLES = []
     */

    static List<Equation> sInitialEquations;
    static TargetFunction sInitialTargetFunction;
    static List<Boolean> sInitialFakeVariables;

    List<Boolean> isFakeVariableList;
    TargetFunction targetFunction;
    @Getter private List<Equation> equationList;

    @Override
    public String toString() {
        /**
         *  def __str__(self):
         rep = ""
         for equation in self.equations:
            rep += str(equation) + "\n"
         rep += str(self.target_function) + "\n"
         return rep
         */
        StringBuilder sb = new StringBuilder();
        for (Equation equation : equationList)
            sb.append(equation.toString()).append("\n");
        sb.append(targetFunction.toString()).append("\n");
        return sb.toString();

    }

    @Override
    public Iterator<Equation> iterator() {
        List<Equation> newEquationList = new ArrayList<>();
        Collections.copy(newEquationList, equationList);
        newEquationList.add(targetFunction);
        return newEquationList.iterator();
    }

    int size() {
        /**
         *  def __len__(self):
         return len(self.equations) + 1
         */
        return equationList.size() + 1;
    }

    Equation get(int i) {
        /**
         *  def __getitem__(self, item):
         if item < len(self.equations):
            return self.equations[item]
         return self.target_function
         */
        if (i < equationList.size())
            return equationList.get(i);
        return targetFunction;
    }

    void set(int i, Equation equation) {
        /**
         *  def __setitem__(self, key, value):
         if key < len(self.equations):
         self.equations[key] = value
         else:
         self.target_function = value
         */
        if (i < equationList.size())
            equationList.set(i, equation);
        else if (equation instanceof TargetFunction)
            targetFunction = (TargetFunction) equation;
    }

    void add(Equation equation) {
        /**
         *  def add(self, equation):
         """
         Adds equation to end of the list.
         :param equation: equation
         :return: None.
         """
         self.equations.append(equation)
         */
        equationList.add(equation);
    }

    void reloadTargetFunction(boolean isMax) {
        /**
         *  def reload_target_function(self, is_max):
         """
         Reloads target function from initial.
         :param is_max: if is searching max value.
         :return: None.
         """
         self.target_function = TargetFunction([])
         for i in range(len(self.is_fake_values)-1):
         if not isinstance(EquationSystem.INITIAL_TARGET_FUNCTION[i], M) \
         and is_max:
            self.target_function.add_coefficient(
            -EquationSystem.INITIAL_TARGET_FUNCTION[i])
         else:
            self.target_function.add_coefficient(
            EquationSystem.INITIAL_TARGET_FUNCTION[i])
         self.target_function.add_coefficient(
         (-1 if is_max else 1) *
         EquationSystem.INITIAL_TARGET_FUNCTION.get_value())
         */

        List<Coefficient> cofs = new ArrayList<>();
        Coefficient initialCof;
        for (int i = 0; i < sInitialFakeVariables.size() - 1; i++) {
            initialCof = sInitialTargetFunction.getCoefficient(i);
            if (initialCof instanceof M && isMax) {
                targetFunction.addCoefficient(initialCof.negate());
            } else {
                targetFunction.addCoefficient(initialCof);
            }
        }
        targetFunction.setValue(sInitialTargetFunction.getValue()
                .multiply(new Coefficient((isMax) ? -1 : 1)));
    }

    void reloadFakeValues() {
        /**
         *  def reload_fake_values(self):
         """
         Reloads fake values from initial.
         :return: None.
         """
         self.is_fake_values = \
         [is_fake for is_fake in EquationSystem.INITIAL_FAKE_VARIABLES]
         */
        Collections.copy(isFakeVariableList, sInitialFakeVariables);
    }

    void reloadEquations() {
        /**
         *  def reload_equations(self):
         """
         Reloads equation list from initial.
         :return: None
         """
         self.equations = \
         [Equation([value for value in row])
         for row in EquationSystem.INITIAL_EQUATIONS]
         */
        Collections.copy(equationList, sInitialEquations);
    }

    static void setInitialFakeValues(List<Boolean> isFakeVariableList) {
        /**
         *  @staticmethod
        def set_initial_fake_values(f_v):
        """
        Sets initial fake values state list.
        :param f_v: fake values state list.
        :return: None.
        """
        EquationSystem.INITIAL_FAKE_VARIABLES = f_v
         */
        sInitialFakeVariables = isFakeVariableList;
    }

    static void setInitialEquations(List<Equation> equations) {
        /**
         *  @staticmethod
        def set_initial_equations(eq_lst):
        """
        Sets initial equation list.
        :param eq_lst: equation list.
        :return: None.
        """
        EquationSystem.INITIAL_EQUATIONS = eq_lst
         */
        sInitialEquations = equations;
    }

    static void setInitialTargetFunction(TargetFunction tfe) {
        /**
         *  @staticmethod
        def set_initial_target_function(t_f):
        """
        Sets initial target function.
        :param t_f: target function.
        :return: None.
        """
        EquationSystem.INITIAL_TARGET_FUNCTION = t_f
         */
        sInitialTargetFunction = tfe;
    }
}
