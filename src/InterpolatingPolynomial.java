import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class InterpolatingPolynomial extends Polynomial {
    private final ArrayList<Point2D> points = new ArrayList<>();
    
    // Кэш для значений функции нахождения разделённой разности
    private final Map<Pair, Double> cache = new HashMap<>();
    
    private Polynomial currentBracketPolynomial = new Polynomial(1.0);
    
    public InterpolatingPolynomial() {
        
    }

    public InterpolatingPolynomial(ArrayList<Point2D> points) {
        this.points.addAll(points);
        calculatePolynomial();
    }

    public InterpolatingPolynomial(Point2D ... points) {
        this.points.addAll(Arrays.asList(points));
        calculatePolynomial();
    }

    public ArrayList<Point2D> getPoints() {
        return new ArrayList<>(points);
    }

    public Double dividedDifference(int start, int end){
        Pair key = new Pair(start, end);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        
        Double result = computeDividedDifference(start, end);
        cache.put(key, result);
        return result;
    }
    
    // Рекурсивная функция нахождения разделённой разности, реализованная по формулам в README.md
    // Есть проблема оптимизации - мы заново вычисляем то, что вычисляли ранее (решение - можно кэшировать функцию)
    public Double computeDividedDifference(int start, int end) {
        if (end - start == 1) {
            return points.get(start).getY();
        }
        else if (end - start > 1) {
            // Убираем первую точку из массива (p1, ..., pn)
            var func1 = dividedDifference(start + 1, end);
            
            // Убираем последнюю точку из массива (p0, ... p(n-1))
            var func2 = dividedDifference(start, end - 1);
            
            return (func1 - func2) /
                    (points.get(end - 1).getX() - points.get(start).getX());
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
    
    // Расчет Pn(x)
    public void calculatePolynomial() {
        currentBracketPolynomial = new Polynomial(1.0);
        var resultPolynomial = new Polynomial(new ArrayList<>());
        for (int i = 0; i < points.size(); i++) {

            // TODO: вынести логику отдельно (1)
            // Первое слагаемое без умножения на скобки
            if (i == 0) {
                resultPolynomial = new Polynomial(dividedDifference(i, i + 1));
            }
            
            // Следующие слагаемые с умножением на скобки (x - xi)
            else {
                currentBracketPolynomial = multiplyPolynomialByBracket(currentBracketPolynomial, points.get(i - 1).getX());
                var additionPolynomial = new Polynomial(currentBracketPolynomial.getCoefficients());
                additionPolynomial.times(dividedDifference(0, i + 1));
                resultPolynomial = Polynomial.plus(
                        resultPolynomial,
                        additionPolynomial
                );
            }
        }
        setCoefficients(resultPolynomial.getCoefficients());
    }
    
    // Укороченная версия алгоритма calculatePolynomial
    public void addPoint(Point2D point) {
        points.add(point);
        Polynomial additionPolynomial;
        
        // TODO: вынести логику отдельно (2)
        if (points.size() == 1) {
            additionPolynomial = new Polynomial(dividedDifference(0, points.size()));
        }
        else {
            var nextIndex = Math.max(0, points.size() - 2);
            currentBracketPolynomial = multiplyPolynomialByBracket(currentBracketPolynomial, points.get(nextIndex).getX());
            additionPolynomial = new Polynomial(currentBracketPolynomial.getCoefficients());
            additionPolynomial.times(dividedDifference(0, points.size()));
        }

        setCoefficients(
            Polynomial.plus(
                this,
                additionPolynomial
            ).getCoefficients()
        );
    }
    
    public void removePoint(Point2D point) {
        
        // пересчитывать полином, если точка удалена
        if (points.remove(point)){
            calculatePolynomial();
        }
    }
}
