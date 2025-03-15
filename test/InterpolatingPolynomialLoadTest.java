import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

class InterpolatingPolynomialLoadTest {
    
    @Test
    void testComplex(){
        var random = new Random();
        var startPointsCount = 100;
        
        for (int i = 1; i < 8; i++) {
            var points = new ArrayList<Point2D>();
            for (int j = 0; j < i * startPointsCount; j++) {
                points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }
            
            var startTime = System.nanoTime();
            var polynomial = new InterpolatingPolynomial(points);
            polynomial.clearCache();
            var endTime = System.nanoTime();
            
            long duration = endTime - startTime;
            System.out.println(i * startPointsCount + " точек: " + duration / 1_000_000_000.0 + " с");
        }
    }
    
    @Test
    void testBigRandomData(){
        var random = new Random();

        var startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            var points = new ArrayList<Point2D>();
            for (int j = 0; j < 100; j++) {
                points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }

            var polynomial = new InterpolatingPolynomial(points);
            polynomial.clearCache();
        }
        var endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Время выполнения: " + duration / 1_000_000_000.0 + " с");
    }

}