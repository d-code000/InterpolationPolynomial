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
            
            System.out.println(i * startPointsCount + " точек: " + getDurationInSec(startTime, endTime) + " с");
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
        
        for (int i = 2; i <= 50; i++) {

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
    
    @Test
    void removeRandomPoint() {
        var pointsCount = 500;
        
        var random = new Random();
        var points = new ArrayList<Point2D>();
        
        for (int i = 0; i < pointsCount; i++) {
            points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
        }
        
        var polynomial = new InterpolatingPolynomial(points);

        var startTime = System.nanoTime();
        polynomial.removePoint(points.get(random.nextInt(pointsCount)));
        var endTime = System.nanoTime();
        
        System.out.println("Время удаления: " + getDurationInSec(startTime, endTime));
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
            new InterpolatingPolynomial(points);
        }
        var endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Время выполнения: " + duration / 1_000_000_000.0 + " с");
    }

}