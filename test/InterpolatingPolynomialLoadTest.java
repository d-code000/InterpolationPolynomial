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
        var startPointCount = 1400;
        var stepPointsCount = 200;
        
        var random = new Random();
        
        for (int i = 0; i < 10; i++) {
            var points = new ArrayList<Point2D>();
            var countPoints = startPointCount + (i * stepPointsCount);
            for (int j = 0; j <  countPoints; j++) {
                points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }
            
            var startTime = System.nanoTime();
            new InterpolatingPolynomial(points);
            var endTime = System.nanoTime();
            
            System.out.printf(
                    "Построение нового полинома из %d точек: %.2f c\n",
                    countPoints,
                    getDurationInSec(startTime, endTime));
        }
    }
    
    @Test
    void testAddPoints(){
        var startPointCount = 1000;
        var stepPointsCount = 200;
        
        var random = new Random();
        
        var points = new ArrayList<Point2D>();
        for (int i = 0; i < startPointCount; i++) {
            points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
        }
        
        var polynomial = new InterpolatingPolynomial(points);
        
        System.out.printf(
                "Начальное число точек в полиноме: %d\n",
                startPointCount
        );
        
        for (int i = 0; i < 10; i++) {

            var startTime = System.nanoTime();
            for (int j = 0; j < stepPointsCount; j++) {
                polynomial.addPoint(new Point2D.Double(random.nextDouble(), random.nextDouble()));
            }
            var endTime = System.nanoTime();
            System.out.printf(
                    "Добавление %d точек в полином (текущий размер %d): %.2f c\n",
                    stepPointsCount,
                    polynomial.getPoints().size(),
                    getDurationInSec(startTime, endTime)
            );
        }
    }
}