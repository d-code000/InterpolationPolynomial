import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PolynomialUnitTest {
    
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
    void testEquals(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p3 = new Polynomial(1.0, 2.0, 4.0);
        
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }
    
    @Test
    void testHashCode(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 2.0, 3.0);
        assertEquals(p1.hashCode(), p2.hashCode());
        
        var oldHash = p1.hashCode();
        p1.setCoefficients(new ArrayList<>(List.of(1.5, 2.5, 3.5)));
        assertNotEquals(oldHash, p1.hashCode());
    }
    
    @Test
    void testGetDegree(){
        Polynomial p = new Polynomial(1.0, 2.0, 3.0);
        assertEquals(2, p.getDegree());
    }
    
    @Test
    void testPlusEqual(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 2.0, 3.0);
        
        p1.plus(p2);
        assertEquals(new ArrayList<>(List.of(2.0, 4.0, 6.0)), p1.getCoefficients());
    }
    
    @Test
    void TestPlusMore(){
        Polynomial p1 = new Polynomial(1.0);
        Polynomial p2 = new Polynomial(1.0, 2.0, 3.0);

        p1.plus(p2);
        assertEquals(new ArrayList<>(List.of(2.0, 2.0, 3.0)), p1.getCoefficients());
    }
    
    @Test
    void testMinusEqual(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 1.0, 1.0);
        
        p1.minus(p2);
        assertEquals(new ArrayList<>(List.of(0.0, 1.0, 2.0)), p1.getCoefficients());
    }
    
    @Test
    void testMinusMore(){
        Polynomial p1 = new Polynomial(1.0);
        Polynomial p2 = new Polynomial(1.0, 1.0, 1.0);

        p1.minus(p2);
        assertEquals(new ArrayList<>(List.of(0.0, -1.0, -1.0)), p1.getCoefficients());
    }
    
    @Test
    void testMultiplyEqual(){
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(1.0, 1.5, 2.0);
        
        p1.times(p2);
        assertEquals(new ArrayList<>(List.of(1.0, 3.0, 6.0)), p1.getCoefficients());
    }
    
    @Test
    void testMultiplyMore(){
        Polynomial p1 = new Polynomial(1.0);
        Polynomial p2 = new Polynomial(1.0, 3.0, 6.0);
        
        p1.times(p2);
        assertEquals(new ArrayList<>(List.of(1.0, 3.0, 6.0)), p1.getCoefficients());
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