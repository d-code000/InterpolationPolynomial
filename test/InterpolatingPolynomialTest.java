import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpolatingPolynomialTest {

    @Test
    void testDividedDifference() {
        assertEquals(
                2,
                InterpolatingPolynomial.dividedDifference(
                        new Point2D.Double(1, 2)
                )
        );
        assertEquals(
                1,
                InterpolatingPolynomial.dividedDifference(
                        new Point2D.Double(1, 2),
                        new Point2D.Double(2, 3)
                )
        );
        assertEquals(
                2,
                InterpolatingPolynomial.dividedDifference(
                        new Point2D.Double(2, 3),
                        new Point2D.Double(3, 5)
                )
        );
        assertEquals(
                0.5, 
                InterpolatingPolynomial.dividedDifference(
                        new Point2D.Double(1, 2),
                        new Point2D.Double(2, 3),
                        new Point2D.Double(3, 5)
                )
        );
    }
    
    @Test
    void testMultiplyPolynomialByBracket(){
        assertEquals(
                new ArrayList<>(List.of(-4.0, 1.0)),
                InterpolatingPolynomial.multiplyPolynomialByBracket(
                        new Polynomial(1.0),
                        4.0
                ).getCoefficients()
        );
        assertEquals(
                new ArrayList<>(List.of(24.0, -13.0, -1.0, 2.0)),
                InterpolatingPolynomial.multiplyPolynomialByBracket(
                        new Polynomial(8.0, -7.0, 2.0),
                        -3.0
                ).getCoefficients()
        );
    }
    
    @Test
    void testCalculatePolynomial() {
        assertEquals(
                new ArrayList<>(List.of(2.0, -0.5, 0.5)),
                InterpolatingPolynomial.calculatePolynomial(
                        new Point2D.Double(1, 2),
                        new Point2D.Double(2, 3),
                        new Point2D.Double(3, 5)
                ).getCoefficients()
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