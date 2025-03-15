import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class InterpolatingPolynomial extends Polynomial {
    private ArrayList<Point2D> points = new ArrayList<>();

    public ArrayList<Point2D> getPoints() {
        return new ArrayList<>(points);
    }
    
    // рекурсивная функция нахождения разделённых разностей, реализованная по формулам в README.md
    private Double dividedDifference(Point2D ... points) {
        if (points.length == 2) {
            return (points[1].getY() - points[0].getY()) / 
                    (points[1].getX() - points[0].getX());
        }
        else {
            // Убираем первую точку из массива (p1, ..., pn)
            var func1 = dividedDifference(Arrays.copyOfRange(points, 1, points.length));
            
            // Убираем последнюю точку из массива (p0, ... p(n-1))
            var func2 = dividedDifference(Arrays.copyOfRange(points, 0, points.length - 1));
            
            return (func1 - func2) /
                    (points[points.length - 1].getX() - points[0].getX());
        }
    }
    
}
