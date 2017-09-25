package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


class EquationSystem implements Iterable<Equation> {

    /**
     *  # Static list for storing initial equation list
     INITIAL_EQUATIONS = []
     # Static TargetFunction for storing initial target function
     INITIAL_TARGET_FUNCTION = TargetFunction([])
     # Static list for storing initial list of fake variables states
     INITIAL_FAKE_VARIABLES = []
     */

    @Getter private List<Boolean> isFakeVariableList;
    @Getter @Setter private TargetFunction targetFunction;
    @Getter private List<Equation> equationList;

    public EquationSystem(List<Equation> equations, List<Boolean> fakeVariables, TargetFunction targetFunction) {
        equationList = equations;
        isFakeVariableList = fakeVariables;
        this.targetFunction = targetFunction;
    }

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

    public int size() {
        /**
         *  def __len__(self):
         return len(self.equations) + 1
         */
        return equationList.size() + 1;
    }

    public Equation get(int i) {
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

    public void set(int i, Equation equation) {
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

    public void add(Equation equation) {
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


}
