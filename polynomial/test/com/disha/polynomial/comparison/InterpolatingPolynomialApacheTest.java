package com.disha.polynomial.comparison;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;
import com.disha.polynomial.InterpolatingPolynomial;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Тесты для сравнения результатов InterpolatingPolynomial c мат. модулем Apache
public class InterpolatingPolynomialApacheTest {
    
    @RepeatedTest(10)
    public void testComparisonMyClassWithApache() {

        int numPoints = 5000;

        var random = new Random();
        
        double[] x = new double[numPoints];
        double[] y = new double[numPoints];
        var points = new ArrayList<Point2D>();
        
        for (int i = 0; i < numPoints; i++) {
            x[i] = random.nextDouble();
            y[i] = random.nextDouble();
        }
        
        x = Arrays.stream(x).sorted().toArray();
        for (int i = 0; i < numPoints; i++) {
            points.add(new Point2D.Double(x[i], y[i]));
        }
        
        var interpolator = new DividedDifferenceInterpolator();
        var apachePolynomial = interpolator.interpolate(x, y);
        var apacheCoefficients = apachePolynomial.getCoefficients();
        
        var myPolynomial = new InterpolatingPolynomial(points);
        
        var myCoefficients = myPolynomial.getCoefficients().stream().mapToDouble(Double::doubleValue).toArray();

        Assertions.assertArrayEquals(apacheCoefficients, myCoefficients);
    }
}
