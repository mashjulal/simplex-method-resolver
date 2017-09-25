package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.M;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;


public class TargetFunctionTest {

    private static TargetFunction getTargetFunction() {
        ArrayList<Coefficient> coefficientList = new ArrayList<>();
        coefficientList.add(new Coefficient(-8));
        coefficientList.add(new Coefficient(6));
        return new TargetFunction(coefficientList, new Coefficient(0));
    }

    @Test
    public void toString_isCorrect() throws Exception {
        TargetFunction tf = getTargetFunction();
        assertEquals("F - 8x1 + 6x2 = 0", tf.toString());

        tf.addCoefficient(new Coefficient(1));
        tf.addCoefficient(new M(10, 3));

        assertEquals("F - 8x1 + 6x2 + x3 + (10M + 3)x4 = 0", tf.toString());
    }

    @Test
    public void index_isCorrect() throws Exception {
        TargetFunction tf = getTargetFunction();
        assertEquals(0, tf.index(new Coefficient(-8)));
        assertEquals(1, tf.index(new Coefficient(6)));
        assertEquals(-1, tf.index(new Coefficient(-6)));
    }

}