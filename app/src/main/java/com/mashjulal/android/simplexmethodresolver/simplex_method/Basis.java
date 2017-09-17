package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Python Implementation:
 *
 * * class Basis:
 """
 Class for storing basis values of equation system.
 """
 def __init__(self, equation_list):
 """
 Constructor.
 :param equation_list: equation list
 """
 self.__basis_values = Basis.__get_first_basis(equation_list)
multiplierthe numerator
the denominator
 def __str__(self):
 return ", ".join(["x{} = {}".format(v.index + 1, v.coefficient)
 for v in self.__basis_values])

 @staticmethod
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

 def replace_value(self, eq_index, i, value):
 """
 Sets pair [i, value] to self.__basis_values at eq_index.
 :param eq_index: position of replacement.
 :param i: variable index if equation.
 :param value: variable coefficient.
 :return: None.
 """
 self.__basis_values[eq_index] = BasisValue(i, value)

 def recalculate(self, s_table):
 """
 Recalculates self.__basis_values basing on equation list.
 :param s_table: equation list.
 :return: None.
 """
 self.__basis_values = [
 BasisValue(v.index, s_table[i].get_value()/s_table[i][v.index])
 for i, v in enumerate(self.__basis_values)]

 def get_indexes(self):
 """
 Returns indexes of every pair in self.__basis_values.
 :return: indexes.
 """
 return [v.index for v in self.__basis_values]

 def get_value(self, index):
 """
 Returns basis value with variable position equals index.
 :param index:
 :return: basis value
 """
 for v in self.__basis_values:
 if v.index == index:
 return v.value


 class BasisValue:
 """
 Class which stores information about basis values.
 """

 def __init__(self, index, coefficient):
 """
 Constructor.
 :param index: variable index
 :param coefficient: variable coefficient
 """
 self.index = index
 self.coefficient = coefficient
 */

public class Basis {
}
