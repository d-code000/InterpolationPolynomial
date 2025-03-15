import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class InterpolatingPolynomial extends Polynomial {
    private ArrayList<Point2D> points = new ArrayList<>();

    public ArrayList<Point2D> getPoints() {
        return new ArrayList<>(points);
    }
    
    // Рекурсивная функция нахождения разделённой разности, реализованная по формулам в README.md
    // Есть проблема оптимизации - мы заново вычисляем то, что вычисляли ранее (решение - можно кэшировать функцию)
    public static Double dividedDifference(Point2D ... points) {
        if (points.length == 1) {
            return points[0].getY();
        }
        else if (points.length == 2) {
            return (points[1].getY() - points[0].getY()) / 
                    (points[1].getX() - points[0].getX());
        }
        else if (points.length > 2) {
            // Убираем первую точку из массива (p1, ..., pn)
            var func1 = dividedDifference(Arrays.copyOfRange(points, 1, points.length));
            
            // Убираем последнюю точку из массива (p0, ... p(n-1))
            var func2 = dividedDifference(Arrays.copyOfRange(points, 0, points.length - 1));
            
            return (func1 - func2) /
                    (points[points.length - 1].getX() - points[0].getX());
        }
        else {
            throw new IllegalArgumentException("Функция нахождения разделённых разностей требует хотя бы одну точку");
        }
    }
    
    // B(x) = A(x) * (x - xi))
    // Умножение полинома на скобку (x - xi)
    public static Polynomial multiplyPolynomialByBracket(Polynomial polynomial, Double xi) {
        
        // A(x) * x
        var firstCoefficient = polynomial.getCoefficients();
        firstCoefficient.addFirst(0.0);
        var firstPolynomial = new Polynomial(firstCoefficient);
        
        // A(x) * (-xi)
        var secondPolynomial = new Polynomial(polynomial.getCoefficients());
        secondPolynomial.times(-xi);
        
        return Polynomial.plus(firstPolynomial, secondPolynomial);
    }
    
    
}
