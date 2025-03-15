import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class InterpolatingPolynomialTest {

    @Test
    void testDividedDifference() {
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
}