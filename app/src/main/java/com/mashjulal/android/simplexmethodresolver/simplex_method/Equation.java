package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.List;

/**
 * Python Implementation
 *
 * from fractions import Fraction


 class Equation:
 """
 Class for storing equation coefficients and perform operations with them.
 """

 def __init__(self, coefficients):
 """
 Constructor.
 :param coefficients: equation coefficients
 """
 self.coefficients = coefficients

 def __add__(self, other):
 if isinstance(other, Fraction):
 return Equation(
 self.get_coefficients() + [self.get_value() + other])
 elif isinstance(other, Equation):
 return Equation(
 [c + other[i] for i, c in enumerate(self.coefficients)])

 __radd__ = __add__

 def __iadd__(self, other):
 if isinstance(other, Fraction):
 self.coefficients = \
 self.get_coefficients() + [self.get_value() + other]
 elif isinstance(other, Equation):
 self.coefficients = \
 [c + other[i] for i, c in enumerate(self.coefficients)]

 def __sub__(self, other):
 if isinstance(other, Fraction):
 return Equation(
 self.get_coefficients() + [self.get_value() - other])
 elif isinstance(other, Equation):
 return Equation(
 [c - other[i] for i, c in enumerate(self.coefficients)])

 def __rsub__(self, other):
 if isinstance(other, Fraction):
 return Equation(
 [self.get_coefficients()] + [other - self.get_value()])
 elif isinstance(other, Equation):
 return Equation(
 [other[i] - c for i, c in enumerate(self.coefficients)])

 def __mul__(self, other):
 if isinstance(other, Fraction):
 return Equation(
 [other * c for c in self.coefficients])
 else:
 raise RuntimeError("Unsupported operation.")

 __rmul__ = __mul__

 def __truediv__(self, other):
 if isinstance(other, Fraction):
 return Equation(
 [c / other for c in self.coefficients])
 else:
 raise RuntimeError("Unsupported operation.")

 def __neg__(self):
 return Equation([-c for c in self.coefficients])

 def __getitem__(self, item):
 return self.coefficients[item]

 def __str__(self):
 rep = ""
 for i, c in enumerate(self.get_coefficients()):
 if c != 0:
 rep += "{} ".format("+" if c > 0 else "-")
 if abs(c) != 1:
 rep += "{}".format(abs(c))
 rep += "x{} ".format(i + 1)
 if rep[0] == "+":
 rep = rep[2:]
 return rep + "= {}".format(self.get_value())

 def __len__(self):
 return len(self.coefficients)

 def __delitem__(self, key):
 del self.coefficients[key]

 def __setitem__(self, key, value):
 self.coefficients[key] = value

 def __iter__(self):
 self.i = 0
 return self

 def __next__(self):
 if self.i == len(self.coefficients):
 raise StopIteration
 result = self.coefficients[self.i]
 self.i += 1
 return result

 def get_value(self):
 """
 Returns value of equation.
 :return: value.
 """
 return self.coefficients[-1]

 def get_coefficients(self):
 """
 Returns list of equation coefficients.
 :return: list of coefficients.
 """
 return self.coefficients[:-1]

 def add_coefficient(self, c):
 """
 Adds coefficient to end of coefficient list.
 :param c: variable coefficient.
 :return: None.
 """
 self.coefficients.append(c)

 def express(self, indx):
 """
 Returns expressed equation where each coefficient
 is divided by coefficient with index indx.
 :param indx: index of coefficient.
 :return: expressed equation.
 """
 r_c = self.coefficients[indx]
 return Equation([-c / r_c if i != indx else 0
 for i, c in enumerate(self.coefficients)])
 */

public class Equation {

    private List<Fraction> coefficients;
    private Fraction value;

    public List<Fraction> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Fraction> coefficients) {
        this.coefficients = coefficients;
    }

    public Fraction getValue() {
        return value;
    }

    public void setValue(Fraction value) {
        this.value = value;
    }

    public int size() {
        return coefficients.size();
    }
}
