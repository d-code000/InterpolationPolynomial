import org.junit.jupiter.api.Test;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// Тесты для сравнения результатов InterpolatingPolynomial c мат. модулем Apache
public class InterpolatingPolynomialApacheTest {
    
    public static double normalizeMantissa(double value) {
        int exponent = Math.getExponent(value);
        return value / Math.pow(2, exponent);
    }
    
    @Test
    public void testInterpolatingPolynomial() {

        int numPoints = 5000;

        var rand = new Random();
        
        double[] x = new double[numPoints];
        double[] y = new double[numPoints];
        var points = new ArrayList<Point2D>();
        
        for (int i = 0; i < numPoints; i++) {
            x[i] = rand.nextDouble();
            y[i] = rand.nextDouble();
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
        
        for (int i = 0; i < numPoints; i++) {
            apacheCoefficients[i] = normalizeMantissa(apacheCoefficients[i]);
            myCoefficients[i] = normalizeMantissa(myCoefficients[i]);
        }

        assertArrayEquals(apacheCoefficients, myCoefficients, 1e-6);
    }
}
