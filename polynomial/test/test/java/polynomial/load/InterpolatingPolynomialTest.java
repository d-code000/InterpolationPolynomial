package test.java.polynomial.load;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.polynomial.InterpolatingPolynomial;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

class InterpolatingPolynomialTest {
    
    private double getDurationInSec(long start, long end) {
        long duration = end - start;
        return (duration / 1_000_000_000.0);
    }
    
    private ArrayList<Point2D> genRandomPoints(int numPoints) {
        var points = new ArrayList<Point2D>();
        var random = new Random();
        for (int i = 0; i < numPoints; i++) {
            points.add(new Point2D.Double(random.nextDouble(), random.nextDouble()));
        }
        return points;
    }
    
    @Test
    void testCalculateComplex(){
        var startPointCount = 4000;
        var stepPointsCount = 250;
        var countPointsForApproximation = new ArrayList<Integer>();
        var timePointsForApproximation = new ArrayList<Double>();
        
        for (int i = 0; i < 20; i++) {
            var countPoints = startPointCount + (i * stepPointsCount);
            var points = genRandomPoints(countPoints);
            
            var startTime = System.nanoTime();
            new InterpolatingPolynomial(points);
            var endTime = System.nanoTime();
            
            countPointsForApproximation.add(countPoints);
            timePointsForApproximation.add(getDurationInSec(startTime, endTime));
            
            System.out.printf(
                    "Построение нового полинома из %d точек: %.2f c\n",
                    countPointsForApproximation.get(i),
                    timePointsForApproximation.get(i));
        }
        
        System.out.println();
        System.out.println("Данные для аппроксимации:");
        System.out.println(countPointsForApproximation);
        System.out.println(timePointsForApproximation);
    }
    
    @Test
    void testAddPointsComplex(){
        var startPointCount = 5000;
        var stepPointsCount = 500;

        var countPointsForApproximation = new ArrayList<Integer>();
        var timePointsForApproximation = new ArrayList<Double>();
        
        var polynomial = new InterpolatingPolynomial(genRandomPoints(startPointCount));
        
        System.out.printf(
                "Начальное число точек в полиноме: %d\n",
                startPointCount
        );
        
        for (int i = 0; i < 20; i++) {
            var points = genRandomPoints(stepPointsCount);
            
            var startTime = System.nanoTime();
            for (Point2D point : points) {
                polynomial.addPoint(point);
            }
            var endTime = System.nanoTime();
            
            countPointsForApproximation.add(polynomial.getPoints().size());
            timePointsForApproximation.add(getDurationInSec(startTime, endTime));
            
            System.out.printf(
                    "Добавление %d точек в полином (текущий размер %d): %.2f c\n",
                    stepPointsCount,
                    countPointsForApproximation.get(i),
                    timePointsForApproximation.get(i)
            );
        }

        System.out.println();
        System.out.println("Данные для аппроксимации:");
        System.out.println(countPointsForApproximation);
        System.out.println(timePointsForApproximation);
    }
    
    @Test
    void testCalculateVsAddPoint(){
        var countPoints = 10000;
        
        var points = genRandomPoints(countPoints);

        var startTime = System.nanoTime();
        var polynomial1 = new InterpolatingPolynomial(points);
        var endTime = System.nanoTime();

        System.out.printf(
                "Построение нового полинома из %d точек (способ 1): %.2f c\n",
                countPoints,
                getDurationInSec(startTime, endTime));
        
        var polynomial2 = new InterpolatingPolynomial();

        startTime = System.nanoTime();
        for (Point2D point : points) {
            polynomial2.addPoint(point);
        }
        endTime = System.nanoTime();

        System.out.printf(
                "Построение нового полинома из %d точек (способ 2): %.2f c\n",
                countPoints,
                getDurationInSec(startTime, endTime));

        Assertions.assertEquals(polynomial1, polynomial2);
    }
}