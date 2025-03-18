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
        try {
            for (Point2D point : points) {
                addNewPoint(point);
            }
        }
        catch (IllegalArgumentException exception) {
            points.clear();
            throw exception;
        }
        calculatePolynomial();
    }

    public InterpolatingPolynomial(Point2D ... points) {
        this(new ArrayList<>(Arrays.asList(points)));
    }

    public ArrayList<Point2D> getPoints() {
        return new ArrayList<>(points);
    }

    private void addNewPoint(Point2D point) {
        for (Point2D existingPoint : points) {
            if (existingPoint.getX() == point.getX()) {
                throw new IllegalArgumentException("Точка с x = " + point.getX() + " уже добавлена в полином!");
            }
        }
        points.add(point);
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
        else {
            // Убираем первую точку из массива (p1, ..., pn)
            var func1 = dividedDifference(start + 1, end);
            
            // Убираем последнюю точку из массива (p0, ... p(n-1))
            var func2 = dividedDifference(start, end - 1);
            
            return (func1 - func2) /
                    (points.get(end - 1).getX() - points.get(start).getX());
        }
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
            currentBracketPolynomial.times(new Polynomial(-points.get(nextIndex).getX(), 1.0));
            additionPolynomial = currentBracketPolynomial.getCopy();
            additionPolynomial.times(dividedDifference(0, end));
        }
        this.plus(additionPolynomial);
    }
    
    private void calculatePolynomial() {
        currentBracketPolynomial = new Polynomial(1.0);
        setCoefficients(0.0);
        cache.clear();
        for (int i = 0; i < points.size(); i++) {
            plusAdditionPolynomial(i, i + 1);
        }
    }
    
    public void addPoint(Point2D point) {
        this.addNewPoint(point);
        plusAdditionPolynomial(0, points.size());
    }
    
    public void removePoint(Point2D point) {
        if (points.remove(point)){
            calculatePolynomial();
        }
    }
}
