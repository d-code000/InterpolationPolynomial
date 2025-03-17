import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class InterpolatingPolynomial extends Polynomial {
    private final ArrayList<Point2D> points = new ArrayList<>();
    
    // Кэш для значений функции нахождения разделённой разности
    private final Map<Integer, Double> cache = new HashMap<>();
    
    private Polynomial currentBracketPolynomial = new Polynomial(1.0);
    
    public InterpolatingPolynomial() {
        super();
    }

    public InterpolatingPolynomial(ArrayList<Point2D> points) {
        super();
        this.points.addAll(points);
        calculatePolynomial();
    }

    public InterpolatingPolynomial(Point2D ... points) {
        super();
        this.points.addAll(Arrays.asList(points));
        calculatePolynomial();
    }

    public ArrayList<Point2D> getPoints() {
        return new ArrayList<>(points);
    }
    
    // Кэширование функции разделенной разности позволяет избежать проблемы, 
    // когда мы заново вычисляем данные, которые вычисляли ранее
    private Double dividedDifference(int start, int end){
        Integer key = start * points.size() + end;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        
        Double result = computeDividedDifference(start, end);
        cache.put(key, result);
        return result;
    }
    
    // Рекурсивная функция нахождения разделённой разности, реализованная по формулам в README.md
    private Double computeDividedDifference(int start, int end) {
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
    
    // TODO: Заменить на ф-ию умножения полиномов
    // B(x) = A(x) * (x - xi))
    // Умножение полинома на скобку (x - xi)
    public static void multiplyPolynomialByBracket(Polynomial polynomial, Double xi) {
        
        // A(x) * x
        var polynomialCoefficients = polynomial.getCoefficients();
        polynomialCoefficients.addFirst(0.0);
        
        // A(x) * (-xi)
        polynomial.times(-xi);
        
        // (A(x) * (-xi)) + (A(x) * x)
        polynomial.plus(new Polynomial(polynomialCoefficients));
    }

    // Расчет следующего состояния полинома
    private void plusAdditionPolynomial(int start, int end) {

        // Первое слагаемое без умножения на скобки
        Polynomial additionPolynomial;
        if (end == 1) {
            additionPolynomial = new Polynomial(dividedDifference(start, end));
        }

        // Следующие слагаемые с умножением на скобки (x - xi)
        else {
            var nextIndex = Math.max(0, end - 2);
            multiplyPolynomialByBracket(currentBracketPolynomial, points.get(nextIndex).getX());
            additionPolynomial = new Polynomial(currentBracketPolynomial.getCoefficients());
            additionPolynomial.times(dividedDifference(0, end));
        }
        this.plus(additionPolynomial);
    }
    
    private void calculatePolynomial() {
        currentBracketPolynomial = new Polynomial(1.0);
        setCoefficients(0.0);
        for (int i = 0; i < points.size(); i++) {
            plusAdditionPolynomial(i, i + 1);
        }
    }
    
    public void addPoint(Point2D point) {
        points.add(point);
        plusAdditionPolynomial(0, points.size());
    }
    
    public void removePoint(Point2D point) {
        if (points.remove(point)){
            calculatePolynomial();
        }
    }
}
