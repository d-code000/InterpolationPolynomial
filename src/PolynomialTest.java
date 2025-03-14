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
        
        Polynomial p2 = new Polynomial(1.0, 2.0);
        assertEquals("1.0x+2.0", p2.toString());
        
        Polynomial p3 = new Polynomial(1.0, 2.0, 3.0);
        assertEquals("1.0x^2+2.0x+3.0", p3.toString());
        
        Polynomial p4 = new Polynomial(-1.0, -2.0, -3.0);
        assertEquals("-1.0x^2-2.0x-3.0", p4.toString());
    }
}