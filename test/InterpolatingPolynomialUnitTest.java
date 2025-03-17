import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpolatingPolynomialUnitTest {
    
    @Test
    void testGetPointsCopy() {
        var p = new InterpolatingPolynomial(new Point2D.Double(0, 0));
        var points = p.getPoints();
        p.addPoint(new Point2D.Double(1, 1));
        
        assertNotEquals(points, p.getPoints());
    }
    
    @Test
    void testLinearFunction() {
        var polynomial = new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(3, 6)
        );
        // y = 2x
        assertEquals(
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
        assertEquals(
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
        assertEquals(
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
        assertEquals(
                new ArrayList<>(List.of(5.0, 0.0, 0.0)),
                polynomial.getCoefficients()
        );
    }

    @Test
    void testDuplicateXThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new InterpolatingPolynomial(
                    new Point2D.Double(1, 2),
                    new Point2D.Double(1, 3) // Повторяющийся x
            );
        });
    }
    
    @Test
    void testRemovePoint() {
        var ip = new InterpolatingPolynomial(
                new Point2D.Double(1, 2),
                new Point2D.Double(2, 3),
                new Point2D.Double(3, 5)
        );

        assertEquals(
                new ArrayList<>(List.of(2.0, -0.5, 0.5)),
                ip.getCoefficients()
        );
        
        ip.removePoint(new Point2D.Double(3, 5));

        assertEquals(
                new ArrayList<>(List.of(1.0, 1.0)),
                ip.getCoefficients()
        );
    }
    
    @Test
    void testCalculateAndAddPointEqual(){
        var points = new ArrayList<Point2D>();
        points.add(new Point2D.Double(1, 2));
        points.add(new Point2D.Double(2, 3));
        points.add(new Point2D.Double(3, 5));
        
        var polynomial1 = new InterpolatingPolynomial(points);
        var polynomial2 = new InterpolatingPolynomial();
        for (Point2D point : points) {
            polynomial2.addPoint(point);
        }
        
        assertEquals(polynomial1, polynomial2);
        
    }
}