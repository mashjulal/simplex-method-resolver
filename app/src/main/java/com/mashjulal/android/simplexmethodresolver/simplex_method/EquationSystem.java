package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Python Implementation:
 *
 * from M import M
 from equation import Equation
 from target_function_equation import TargetFunctionEquation


 class EquationSystem:
 """
 Class for storing equations and target function
 and perform operations with them.
 """

 # Static list for storing initial equation list
 INITIAL_EQUATIONS = []
 # Static TargetFunctionEquation for storing initial target function
 INITIAL_TARGET_FUNCTION = TargetFunctionEquation([])
 # Static list for storing initial list of fake variables states
 INITIAL_FAKE_VARIABLES = []

 def __init__(self):
 self.is_fake_values = None
 self.equations = None
 self.target_function = None

 def __str__(self):
 rep = ""
 for equation in self.equations:
 rep += str(equation) + "\n"
 rep += str(self.target_function) + "\n"
 return rep

 def __len__(self):
 return len(self.equations) + 1

 def __getitem__(self, item):
 if item < len(self.equations):
 return self.equations[item]
 return self.target_function

 def __setitem__(self, key, value):
 if key < len(self.equations):
 self.equations[key] = value
 else:
 self.target_function = value

 def __iter__(self):
 self.i = 0
 return self

 def __next__(self):
 if self.i == len(self.equations) + len(self.target_function):
 raise StopIteration
 elif self.i < len(self.equations):
 result = self.equations[self.i]
 else:
 result = self.target_function[self.i]
 self.i += 1
 return result

 def add(self, equation):
 """
 Adds equation to end of the list.
 :param equation: equation
 :return: None.
 """
 self.equations.append(equation)

 def get_equations(self):
 """
 Returns equation list.
 :return: list of equations.
 """
 return self.equations

 def reload_target_function(self, is_max):
 """
 Reloads target function from initial.
 :param is_max: if is searching max value.
 :return: None.
 """
 self.target_function = TargetFunctionEquation([])
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

 def reload_fake_values(self):
 """
 Reloads fake values from initial.
 :return: None.
 """
 self.is_fake_values = \
 [is_fake for is_fake in EquationSystem.INITIAL_FAKE_VARIABLES]

 def reload_equations(self):
 """
 Reloads equation list from initial.
 :return: None
 """
 self.equations = \
 [Equation([value for value in row])
 for row in EquationSystem.INITIAL_EQUATIONS]

 @staticmethod
 def set_initial_fake_values(f_v):
 """
 Sets initial fake values state list.
 :param f_v: fake values state list.
 :return: None.
 """
 EquationSystem.INITIAL_FAKE_VARIABLES = f_v

 @staticmethod
 def set_initial_equations(eq_lst):
 """
 Sets initial equation list.
 :param eq_lst: equation list.
 :return: None.
 """
 EquationSystem.INITIAL_EQUATIONS = eq_lst

 @staticmethod
 def set_initial_target_function(t_f):
 """
 Sets initial target function.
 :param t_f: target function.
 :return: None.
 """
 EquationSystem.INITIAL_TARGET_FUNCTION = t_f
 */

public class EquationSystem {
}
