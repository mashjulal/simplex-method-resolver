package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Python Implementation:
 *
 * from fractions import Fraction


 class M:
 """
 Class for very big number.
 """

 # Big number constant
 __BIG_NUMBER = 100000000000000

 def __init__(self, c, v):
 """
 Constructor.
 Creates big number of type cM + v.
 :param c: coefficient before M.
 :param v: value after M.
 """
 self.coefficient = Fraction(c)
 self.value = Fraction(v)

 def __add__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return M(self.coefficient, self.value + other)
 elif isinstance(other, M):
 return M(self.coefficient + other.coefficient,
 self.value + other.value)
 else:
 raise RuntimeError("Unsupported other type: " + type(other))

 __radd__ = __add__

 def __sub__(self, other):
 if isinstance(other, Fraction):
 return M(self.coefficient, self.value - other)
 elif isinstance(other, M):
 return M(self.coefficient - other.coefficient,
 self.value - other.value)
 else:
 raise RuntimeError("Unsupported other type: " + type(other))

 def __rsub__(self, other):
 if isinstance(other, Fraction):
 return M(self.coefficient, self.value - other - self.value)
 elif isinstance(other, M):
 return M(other.coefficient - self.coefficient,
 other.value - self.value)
 else:
 raise RuntimeError("Unsupported other type: " + type(other))

 def __mul__(self, other):
 return M(self.coefficient * other, self.value * other)

 __rmul__ = __mul__

 def __truediv__(self, other):
 return M(self.coefficient / other, self.value / other)

 def __str__(self):
 if self.coefficient != 0:
 if self.value != 0:
 if abs(self.coefficient) == 1:
 return "({}M {} {})".format(
 "" if self.coefficient > 0 else "-",
 ["-", "+"][self.value > 0],
 abs(self.value))
 else:
 return "({}M {} {})".format(
 self.coefficient,
 ["-", "+"][self.value > 0],
 abs(self.value))
 else:
 return "{}M".format(
 self.coefficient if self.coefficient != 1 else "")
 else:
 return str(format(abs(self.value)))

 def __eq__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return self.get_total() == other
 elif isinstance(other, M):
 return self.get_total() == other.get_total()

 def __ne__(self, other):
 return not self.__eq__(other)

 def __lt__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return self.get_total() < other
 elif isinstance(other, M):
 return self.get_total() < other.get_total()

 def __gt__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return self.get_total() > other
 elif isinstance(other, M):
 return self.get_total() > other.get_total()

 def __le__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return self.get_total() <= other
 elif isinstance(other, M):
 return self.get_total() <= other.get_total()

 def __ge__(self, other):
 if isinstance(other, Fraction) or isinstance(other, int):
 return self.get_total() >= other
 elif isinstance(other, M):
 return self.get_total() >= other.get_total()

 def __abs__(self):
 return self if self.coefficient >= 0 else -self

 def __neg__(self):
 return M(-self.coefficient, -self.value)

 def get_total(self):
 """
 Returns total value of cM + v.
 :return: value of cM + v.
 """
 return M.__BIG_NUMBER * self.coefficient + self.value
 */

public class M {
}
