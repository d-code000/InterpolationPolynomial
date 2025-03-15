import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Polynomial {
    protected ArrayList<Double> coefficients;
    
    public Polynomial() {
        this.coefficients = new ArrayList<>(List.of(0.0));
    }
    
    public Polynomial(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
    }
    
    public Polynomial(Double ... coefficients) {
        this.coefficients = new ArrayList<>(List.of(coefficients));
    }

    public ArrayList<Double> getCoefficients() {
        return new ArrayList<>(coefficients);
    }

    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
    }
    
    @Override
    public String toString() {
        var sb = new StringBuilder();
        var n = coefficients.size() - 1;
        
        for (var coefficient : coefficients.reversed()) {
            if (coefficient > 0 && !sb.isEmpty()){
                sb.append("+").append(coefficient);
            }
            
            else {
                sb.append(coefficient);
            }
            
            if (n == 1){
                sb.append("x");
            }
            
            if (n > 1) {
                sb.append("x^").append(n);
            }
            n -= 1;
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return coefficients.equals(((Polynomial) o).coefficients);
    }
    
    @Override
    public int hashCode() {
        return coefficients.hashCode();
    }
    
    private void deleteIndex(int index) {
        coefficients.remove(index);
    }
    
    // степень полинома
    public int getDegree() {
        return coefficients.size() - 1;
    }
    
    // общий метод под разные операции с двумя полиномами
    private static Polynomial operation(Polynomial polynomial1, Polynomial polynomial2, BinaryOperator<Double> operator) {
        var c1 = polynomial1.getCoefficients();
        var c2 = polynomial2.getCoefficients();
        ArrayList<Double> result;
        if (c1.size() >= c2.size()) {
            result = c1;
            for (int i = 0; i < c2.size(); i++) {
                result.set(i, operator.apply(result.get(i), c2.get(i)));
            }
        }
        else {
            result = c2;
            for (int i = 0; i < c1.size(); i++) {
                result.set(i, operator.apply(result.get(i), c1.get(i)));
            }
        }
        return new Polynomial(result);
    }
    
    // общий метод под разные операции с полиномом и числом
    private void operation(Double num, BinaryOperator<Double> operator) {
        coefficients.replaceAll(t -> operator.apply(t, num));
    }
    
    public static Polynomial plus(Polynomial p1, Polynomial p2) {
        return operation(p1, p2, (a, b) -> a + b);
    }
    
    public static Polynomial minus(Polynomial p1, Polynomial p2) {
        return operation(p1, p2, (a, b) -> a - b);
    }
    
    public static Polynomial times(Polynomial p1, Polynomial p2) {
        return operation(p1, p2, (a, b) -> a * b);
    }
    
    public void times(Double num) {
        operation(num, (a, b) -> a * b); 
    }
    
    public void div(Double num) {
        operation(num, (a, b) -> a / b);
    }
    
    public Double calc(Double x) {
        var result = 0.0;
        var n = 0;
        for (var coefficient : coefficients) {
            result += coefficient * Math.pow(x, n);
            n += 1;
        }
        return result;
    }
}
