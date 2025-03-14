import java.util.ArrayList;
import java.util.Arrays;
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
        setCoefficients(new ArrayList<Double>(List.of(coefficients)));
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
}
