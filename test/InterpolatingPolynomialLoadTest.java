import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

class InterpolatingPolynomialLoadTest {
    
    private double getDurationInSec(long start, long end) {
        long duration = end - start;
        return (duration / 1_000_000_000.0);
    }
    
    @Test
    void testComplex(){
        var startPointsCount = 100;
        
        var random = new Random();
        
        for (int i = 1; i <= 20; i++) {
            var points = new ArrayList<Point2D>();
            for (int j = 0; j < i * startPointsCount; j++) {
                points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }
            
            var startTime = System.nanoTime();
            new InterpolatingPolynomial(points);
            var endTime = System.nanoTime();
            
            System.out.printf(
                    "Построение нового полинома из %d точек: %.2f c\n",
                    i * startPointsCount,
                    getDurationInSec(startTime, endTime));
        }
    }
    
    @Test
    void testAddPoints(){
        var startPointsCount = 100;
        
        var random = new Random();
        
        var points = new ArrayList<Point2D>();
        for (int i = 0; i < startPointsCount; i++) {
            points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
        }
        
        var polynomial = new InterpolatingPolynomial(points);
        
        System.out.printf(
                "Начальное число точек в полиноме: %d\n",
                startPointsCount
        );
        
        for (int i = 2; i <= 20; i++) {

            var startTime = System.nanoTime();
            for (int j = 0; j < startPointsCount; j++) {
                polynomial.addPoint(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }
            var endTime = System.nanoTime();
            System.out.printf(
                    "Добавление %d точек в полином (текущий размер %d): %.2f c\n",
                    startPointsCount,
                    polynomial.getPoints().size(),
                    getDurationInSec(startTime, endTime)
            );
        }
    }
}