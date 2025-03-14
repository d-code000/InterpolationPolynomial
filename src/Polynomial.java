import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private ArrayList<Double> coefficients;
    
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

    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
    }
    
    @Override
    public String toString() {
        var sb = new StringBuilder();
        var n = coefficients.size() - 1;
        
        for (var coefficient : coefficients) {
            if (coefficient > 0 && sb.length() != 0){
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
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
    
    private void deleteIndex(int index) {
        throw new UnsupportedOperationException();
    }
    
    private Double getIndex(int index) {
        throw new UnsupportedOperationException();
    }
    
    public static Polynomial plus(Polynomial p1, Polynomial p2) {
        throw new UnsupportedOperationException();
    }
    
    public static Polynomial minus(Polynomial p1, Polynomial p2) {
        throw new UnsupportedOperationException();
    }
    
    public static Polynomial times(Polynomial p1, Polynomial p2) {
        throw new UnsupportedOperationException();
    }
    
    public static Polynomial times(Polynomial p, Double num) {
        throw new UnsupportedOperationException();
    }
    
    public Double calc(){
        throw new UnsupportedOperationException();
    }
}
