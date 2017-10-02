package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


class EquationSystem implements Iterable<Equation> {

    @Getter private IsFakeVariablesList isFakeVariableList;
    @Getter @Setter private TargetFunction targetFunction;
    @Getter private List<Equation> equationList;

    public EquationSystem(List<Equation> equations, IsFakeVariablesList fakeVariables,
                          TargetFunction targetFunction) {
        equationList = equations;
        isFakeVariableList = fakeVariables;
        this.targetFunction = targetFunction;
    }

    @Override
    public String toString() {
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
        return equationList.size() + 1;
    }

    public Equation get(int i) {
        if (i < equationList.size())
            return equationList.get(i);
        return targetFunction;
    }

    public void set(int i, Equation equation) {
        if (i < equationList.size())
            equationList.set(i, equation);
        else if (equation instanceof TargetFunction)
            targetFunction = (TargetFunction) equation;
    }

    public void add(Equation equation) {
        equationList.add(equation);
    }


}
