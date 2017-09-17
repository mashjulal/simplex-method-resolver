package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Python implementation:
 *
 * from equation import Equation


 class TargetFunctionEquation(Equation):
 """
 Class for storing coefficients and value of
 target function and perform operations with it.
 """

 def __str__(self):
 rep = "F"
 for i, c in enumerate(self.get_coefficients()):
 if c != 0:
 rep += " {} {}x{}".format("+" if c > 0 else "-", abs(c), i+1)
 return rep + " = {}".format(self.get_value())

 def index(self, item):
 """
 Returns index of item.
 :param item: value.
 :return: index.
 """
 for i in range(len(self.get_coefficients())):
 if self.coefficients[i] == item:
 return i
 *

 */

public class TargetFunctionEquation extends Equation {
}
