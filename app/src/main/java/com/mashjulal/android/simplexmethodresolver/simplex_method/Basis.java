package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class stores information about equation basis
 * values and perform operations with them.
 */
public class Basis {

    private List<BasisValue> basisValues;

    public Basis(List<Equation> equationList) {
        basisValues = getFirstBasis(equationList);
    }

    private static List<BasisValue> getFirstBasis(List<Equation> equationList) {
        /**
         *  @staticmethod
        def __get_first_basis(e_list):
        """
        Creates first basis from equation list.
        :param e_list: equation list.
        :return: basis values list.
        """
        basis = []
        for equation in e_list:
        basis.append([BasisValue(i, equation.get_value()) for i, c in
        enumerate(equation.get_coefficients()) if c != 0][-1])
        return basis
         */
        List<BasisValue> bv = new ArrayList<>();
        List<BasisValue> coefs;
        for (Equation equation : equationList) {
            coefs = new ArrayList<>();
            for (int coefficientIndex = 0; coefficientIndex < equation.size(); coefficientIndex++) {
                if (equation.getCoefficients().get(coefficientIndex).equals(new Fraction(0)))
                    coefs.add(new BasisValue(coefficientIndex, equation.getValue()));
            }
            bv.add(coefs.get(coefs.size()-1));
        }
        return bv;
    }

    public void replaceValue(int oldEquationIndex,
                             int newEquationIndex, Fraction coefficient) {
        /**
         *  def replace_value(self, eq_index, i, value):
         """
         Sets pair [i, value] to self.__basis_values at eq_index.
         :param eq_index: position of replacement.
         :param i: variable index if equation.
         :param value: variable coefficient.
         :return: None.
         """
         self.__basis_values[eq_index] = BasisValue(i, value)
         */
        basisValues.set(oldEquationIndex, new BasisValue(newEquationIndex, coefficient));
    }

    public void recalculate(List<Equation> equationList) {
        /**
         * def recalculate(self, s_table):
         """
         Recalculates self.__basis_values basing on equation list.
         :param s_table: equation list.
         :return: None.
         """
         self.__basis_values = [
         BasisValue(v.index, s_table[i].get_value()/s_table[i][v.index])
         for i, v in enumerate(self.__basis_values)]
         */

        List<BasisValue> basisValues = new ArrayList<>();
        for (int i = 0; i < this.basisValues.size(); i++) {
            BasisValue bv = this.basisValues.get(i);
            basisValues.add(new BasisValue(
                    bv.getIndex(),
                    equationList.get(i).getValue().divide(
                            equationList.get(i).getCoefficients().get(bv.getIndex()))));
        }
        this.basisValues = basisValues;
    }

    public List<Integer> getIndexes() {
        /**
         *  def get_indexes(self):
         """
         Returns indexes of every pair in self.__basis_values.
         :return: indexes.
         """
         return [v.index for v in self.__basis_values]
         */
        List<Integer> indexes = new ArrayList<>();
        for (BasisValue bv : basisValues)
            indexes.add(bv.getIndex());
        return indexes;
    }

    public Fraction getCoefficient(Fraction coefficient) {
        /**
         def get_value(self, index):
         """
         Returns basis value with variable position equals index.
         :param index:
         :return: basis value
         """
         for v in self.__basis_values:
         if v.index == index:
         return v.value
         */
        for (BasisValue bv : basisValues) {
            if (bv.getCoefficient().equals(coefficient))
                return coefficient;
        }
        return null;
    }

    @Override
    public String toString() {
        /**
         *  def __str__(self):
         return ", ".join(["x{} = {}".format(v.index + 1, v.coefficient)
         for v in self.__basis_values])
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < basisValues.size(); i++) {
            BasisValue bv = basisValues.get(i);
            sb.append(String.format(Locale.getDefault(),
                    "x%d = %s", bv.getIndex() + 1, bv.getCoefficient().toString()));
            if (i < basisValues.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
