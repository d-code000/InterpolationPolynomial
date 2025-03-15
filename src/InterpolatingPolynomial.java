import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterpolatingPolynomial extends Polynomial {
    private final ArrayList<Point2D> points = new ArrayList<>();
    
    @Override
    public void setCoefficients(ArrayList<Double> coefficients) {
        throw new UnsupportedOperationException(
                "Модификация коэффициентов недоступна, т.к они расчитываются на основе точек"
        );
    }
    
    public InterpolatingPolynomial() {
        
    }

    public InterpolatingPolynomial(ArrayList<Point2D> points) {
        this.points.addAll(points);
        this.coefficients = calculatePolynomial(points).getCoefficients();
    }

    public InterpolatingPolynomial(Point2D ... points) {
        this.points.addAll(Arrays.asList(points));
        this.coefficients = calculatePolynomial(points).getCoefficients();
    }

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
    
    public static Double dividedDifference(ArrayList<Point2D> points) {
        return dividedDifference(points.toArray(Point2D[]::new));
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
    
    // Расчет Pn(x)
    public static Polynomial calculatePolynomial(Point2D ... points) {
        Polynomial resultPolynomial = null;
        for (int i = 0; i < points.length; i++) {
            
            // Первое слагаемое без умножения на скобки
            if (i == 0) {
                resultPolynomial = new Polynomial(dividedDifference(points[0]));
            }
            
            // Следующие слагаемые с умножением на скобки (x - xi)
            else {
                var additionPolynomial = new Polynomial(dividedDifference(Arrays.copyOfRange(points, 0, i + 1)));
                for (int j = 0; j < i; j++) {
                    additionPolynomial = multiplyPolynomialByBracket(additionPolynomial, points[j].getX());
                }
                resultPolynomial = Polynomial.plus(
                        resultPolynomial,
                        additionPolynomial
                );
            }
        }
        return resultPolynomial;
    }
    
    public static Polynomial calculatePolynomial(ArrayList<Point2D> points) {
        return calculatePolynomial(points.toArray(Point2D[]::new));
    }
    
    // Укороченная версия алгоритма calculatePolynomial
    public void addPoint(Point2D point) {
        points.add(point);

        var cutPoints = new ArrayList<>(points.subList(0, points.size()));
        var additionPolynomial = new Polynomial(dividedDifference(cutPoints));

        for (int i = 0; i < points.size() - 1; i++) {
            additionPolynomial = multiplyPolynomialByBracket(additionPolynomial, points.get(i).getX());
        }

        coefficients = Polynomial.plus(
                this,
                additionPolynomial
        ).getCoefficients();
    }
    
    public void removePoint(Point2D point) {
        
        // пересчитывать полином, если точка удалена
        if (points.remove(point)){
            var newPolynomial = calculatePolynomial(points);
            
            coefficients = newPolynomial.getCoefficients();
        };
    }
}
