package polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Polynomial {
    private final ArrayList<Double> coefficients = new ArrayList<>();
    
    public Polynomial() {
        setCoefficients(new ArrayList<>(List.of(0.0)));
    }
    
    public Polynomial(ArrayList<Double> coefficients) {
        setCoefficients(coefficients);
    }
    
    public Polynomial(Double ... coefficients) {
        setCoefficients(new ArrayList<>(List.of(coefficients)));
    }

    public ArrayList<Double> getCoefficients() {
        return new ArrayList<>(coefficients);
    }

    public Polynomial getCopy() {
        return new Polynomial(this.getCoefficients());
    }

    protected void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients.clear();
        this.coefficients.addAll(coefficients);
    }
    
    protected void setCoefficients(Double ... coefficients) {
        setCoefficients(new ArrayList<>(List.of(coefficients)));
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
    
    // Степень полинома
    public int getDegree() {
        return coefficients.size() - 1;
    }
    
    // Увеличить степень полинома (равносильно умножению полинома на x^upLevel)
    public void upDegree(int upLevel) {
        for (int i = 0; i < upLevel; i++) {
            coefficients.addFirst(0.0);
        }
    }
    
    // Общий метод под разные операции с двумя полиномами
    private void operation(Polynomial polynomial, BinaryOperator<Double> operator) {
        var polynomialCoefficient = polynomial.getCoefficients();
        
        // Расширяю массивы до одинаковых размеров нейтральными элементами
        while (polynomialCoefficient.size() < coefficients.size()) {
            polynomialCoefficient.addLast(0.0);
        }
        
        while (polynomialCoefficient.size() > coefficients.size()) {
            coefficients.addLast(0.0);
        }
        
        // Вызываю операции поэлементно
        for (int i = 0; i < polynomialCoefficient.size(); i++) {
            coefficients.set(i, operator.apply(coefficients.get(i), polynomialCoefficient.get(i)));
        }
    }
    
    // Общий метод под разные операции с полиномом и числом
    private void operation(Double num, BinaryOperator<Double> operator) {
        coefficients.replaceAll(t -> operator.apply(t, num));
    }
    
    public void plus(Polynomial polynomial) {
        operation(polynomial, Double::sum);
    }
    
    public void minus(Polynomial polynomial) {
        operation(polynomial, (a, b) -> a - b);
    }
    
    public void times(Polynomial polynomial) {
        var startPolynomial = this.getCopy();
        var polynomialCoefficient = polynomial.getCoefficients();
        
        for (int i = 0; i < polynomialCoefficient.size(); i++) {
            if (i == 0) {
                this.times(polynomialCoefficient.get(i));
            }
            else {
                var additionPolynomial = startPolynomial.getCopy();
                additionPolynomial.times(polynomialCoefficient.get(i));
                additionPolynomial.upDegree(i);
                this.plus(additionPolynomial);
            }
        }
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
