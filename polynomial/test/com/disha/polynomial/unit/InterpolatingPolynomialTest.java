package com.disha.polynomial.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.disha.polynomial.InterpolatingPolynomial;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class InterpolatingPolynomialTest {
    
    @Test
    void testGetPointsCopy() {
        var p = new InterpolatingPolynomial(new Point2D.Double(0, 0));
        var points = p.getPoints();
        p.addPoint(new Point2D.Double(1, 1));
        
        Assertions.assertNotEquals(points, p.getPoints());
    }
    
    @Test
    void testLinearFunction() {
        var polynomial = new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(3, 6)
        );
        // y = 2x
        Assertions.assertEquals(
                new ArrayList<>(List.of(0.0, 2.0)),
                polynomial.getCoefficients()
        );
    }

    @Test
    void testQuadraticFunction() {
        var polynomial = new InterpolatingPolynomial(
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(2, 4)
        );
        // y = x^2
        Assertions.assertEquals(
                new ArrayList<>(List.of(0.0, 0.0, 1.0)),
                polynomial.getCoefficients()
        );
    }

    @Test
    void testQuadraticFunction2() {
        var polynomial = new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(2, 3),
                new Point2D.Double(3, 5)
        );
        Assertions.assertEquals(
                new ArrayList<>(List.of(2.0, -0.5, 0.5)),
                polynomial.getCoefficients()
        );
    }

    @Test
    void testConstantFunction() {
        var polynomial = new InterpolatingPolynomial(
                new Point2D.Double(0, 5),
                new Point2D.Double(1, 5),
                new Point2D.Double(2, 5)
        );
        // y = 5
        Assertions.assertEquals(
                new ArrayList<>(List.of(5.0, 0.0, 0.0)),
                polynomial.getCoefficients()
        );
    }

    @Test
    void testDuplicateXThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(1, 3) // Повторяющийся x
        ));
    }
    
    @Test
    void testRemovePoint() {
        var ip = new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(2, 3),
                new Point2D.Double(3, 5)
        );

        Assertions.assertEquals(
                new ArrayList<>(List.of(2.0, -0.5, 0.5)),
                ip.getCoefficients()
        );
        
        ip.removePoint(new Point2D.Double(3, 5));
        
        Assertions.assertEquals(
                new ArrayList<>(List.of(1.0, 1.0)),
                ip.getCoefficients()
        );
        
        ip.removePoint(new Point2D.Double(0, 0));
        Assertions.assertEquals(new ArrayList<>(List.of(new Point2D.Double(1, 2), new Point2D.Double(2, 3))), ip.getPoints());
    }
    
    @Test
    void testCalculateAndAddPointEqual(){
        var points = new ArrayList<Point2D>();
        var random = new Random();
        for (int i = 0; i < 1000; i++) {
            points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
        }
        
        var polynomial1 = new InterpolatingPolynomial(points);
        var polynomial2 = new InterpolatingPolynomial();
        for (Point2D point : points) {
            polynomial2.addPoint(point);
        }
        Assertions.assertArrayEquals(polynomial2.getCoefficients().toArray(new Double[0]), polynomial1.getCoefficients().toArray(new Double[0]));
    }
}