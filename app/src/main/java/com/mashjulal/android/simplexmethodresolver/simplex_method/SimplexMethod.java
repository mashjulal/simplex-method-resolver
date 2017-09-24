package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Python Implementation:
 *
 * import copy
 from fractions import Fraction
 from math import inf

 from M import M
 from basis import Basis
 from equation import Equation
 from equation_system import EquationSystem
 from target_function_equation import TargetFunction


 class SimplexMethod:
 """
 Class which complete work of simplex method.
 """

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

 self.solution = ""

 def start(self):
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

 @staticmethod
 def create_equation_system(c_s, s_c, s_v):
 """
 Create equation list from parameters.
 :param s_c: equation system variable coefficients.
 :param c_s: equation system comparison signs.
 :param s_v: equation system right values.
 :return: None.
 """
 equation_system = []
 for i, row in enumerate(s_c):
 equation_system.append(Equation(
 [Fraction(elem) for elem in row]))

 system_size = len(equation_system)
 for i, sign in enumerate(c_s):
 if sign != "=":
 for j in range(system_size):
 if j == i:
 equation_system[j].add_coefficient(
 Fraction([-1, 1][sign == "<="]))
 else:
 equation_system[j].add_coefficient(Fraction(0))

 for i, sign in enumerate(c_s):
 if sign == ">=":
 for j in range(system_size):
 equation_system[j].add_coefficient(Fraction([0, 1][i == j]))

 for i, v in enumerate(s_v):
 equation_system[i].add_coefficient(v)

 EquationSystem.INITIAL_EQUATIONS = equation_system

 @staticmethod
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

 @staticmethod
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

 def get_estimated_attitude(self, e_index):
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

 def get_new_system(self, eq, val_index, elem_index):
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
 eeqq = [eqq[j]+row[j]
 if j != elem_index else 0 for j in range(len(eqq))]
 if i < len(self.equation_system.equations):
 self.equation_system[i] = Equation(eeqq)
 else:
 self.equation_system[i] = TargetFunction(eeqq)

 def show_solution(self, f_v=0, is_infinite=False, not_optimizable=False):
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

 def remove_fake_values(self, first_fake_index):
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

 def get_first_solution(self):
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

 def get_solution_for_function(self):
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

class SimplexMethod {
}
