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
    void testMultiplyPolynomialByBracket(){
        var polynomial1 = new Polynomial(1.0);
        InterpolatingPolynomial.multiplyPolynomialByBracket(polynomial1, 4.0);
        assertEquals(
                new ArrayList<>(List.of(-4.0, 1.0)),
                polynomial1.getCoefficients()
        );
        
        var polynomial2 = new Polynomial(8.0, -7.0, 2.0);
        InterpolatingPolynomial.multiplyPolynomialByBracket(polynomial2, -3.0);
        assertEquals(
                new ArrayList<>(List.of(24.0, -13.0, -1.0, 2.0)),
                polynomial2.getCoefficients()
        );
    }
    
    @Test
    void testCalculatePolynomial() {
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
    void testAddPoint() {
        var ip = new InterpolatingPolynomial();
        
        ip.addPoint(new Point2D.Double(1, 2));
        assertEquals(
                new ArrayList<>(List.of(2.0)),
                ip.getCoefficients()
        );
        
        ip.addPoint(new Point2D.Double(2, 3));
        assertEquals(
                new ArrayList<>(List.of(1.0, 1.0)),
                ip.getCoefficients()
        );
        
        ip.addPoint(new Point2D.Double(3, 5));
        assertEquals(
                new ArrayList<>(List.of(2.0, -0.5, 0.5)),
                ip.getCoefficients()
        );
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
}