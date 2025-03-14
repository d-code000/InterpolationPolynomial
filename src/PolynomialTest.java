import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PolynomialTest {
    
    @Test
    void testConstructors(){
        Polynomial p = new Polynomial();
        assertEquals(new ArrayList<>(List.of(0.0)), p.getCoefficients());
        
        Polynomial p2 = new Polynomial(new ArrayList<>(List.of(1.0, 2.0, 3.0)));
        assertEquals(new ArrayList<>(List.of(1.0, 2.0, 3.0)), p2.getCoefficients());
        
        Polynomial p3 = new Polynomial(1.0, 2.0, 3.0);
        assertEquals(new ArrayList<>(List.of(1.0, 2.0, 3.0)), p3.getCoefficients());
        
        Polynomial p4 = new Polynomial(new Double[]{1.0, 2.0, 3.0});
        assertEquals(new ArrayList<>(List.of(1.0, 2.0, 3.0)), p4.getCoefficients());
    }
    
    @Test
    void testGetCoefficientsCopy(){
        Polynomial p = new Polynomial(1.0, 2.0, 3.0);
        var coefficientsCopy = p.getCoefficients();
        coefficientsCopy.add(4.0);
        
        assertNotEquals(p.getCoefficients(), coefficientsCopy);
    }
    
    @Test
    void testToString(){
        Polynomial p = new Polynomial(1.0);
        assertEquals("1.0", p.toString());
        
        Polynomial p2 = new Polynomial(2.0, 1.0);
        assertEquals("1.0x+2.0", p2.toString());
        
        Polynomial p3 = new Polynomial(3.0, 2.0, 1.0);
        assertEquals("1.0x^2+2.0x+3.0", p3.toString());
        
        Polynomial p4 = new Polynomial(-3.0, -2.0, -1.0);
        assertEquals("-1.0x^2-2.0x-3.0", p4.toString());
    }
    
    @Test
    void testPlus(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 2.0, 3.0);
        
        var plus = Polynomial.plus(p1, p2);
        assertEquals(new ArrayList<>(List.of(2.0, 4.0, 6.0)), plus.getCoefficients());
    }
    
    @Test
    void testMinus(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 1.0, 1.0);
        
        var minus = Polynomial.minus(p1, p2);
        assertEquals(new ArrayList<>(List.of(0.0, 1.0, 2.0)), minus.getCoefficients());
    }
    
    @Test
    void testMultiply(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 1.5, 2.0);
        
        var multiply = Polynomial.times(p1, p2);
        assertEquals(new ArrayList<>(List.of(1.0, 3.0, 6.0)), multiply.getCoefficients());
    }
    
    @Test
    void testMultiplyNum(){
        Polynomial p = new Polynomial(1.0, 2.0, 3.0);
        p.times(2.0);
        assertEquals(new ArrayList<>(List.of(2.0, 4.0, 6.0)), p.getCoefficients());
    }
    
    @Test
    void testDivideNum(){
        Polynomial p = new Polynomial(1.0, 2.0, 3.0);
        p.div(2.0);
        assertEquals(new ArrayList<>(List.of(0.5, 1.0, 1.5)), p.getCoefficients());
    }
    
    @Test
    void testCalculate(){
        assertEquals(0.0, new Polynomial(0.0).calc(1.0));
        assertEquals(1.0, new Polynomial(1.0).calc(0.0));
        assertEquals(3.0, new Polynomial(1.0, 2.0).calc(1.0));
        assertEquals(47.08, new Polynomial(1.0, 2.0, 3.0).calc(3.6));
    }
}