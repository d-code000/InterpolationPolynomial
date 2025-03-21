package unit;

import converter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConverterTest {
    
    private Converter[] getConverters(){
        return new Converter[]{
                new Converter(-1.0, 1.0, -1.0, 1.0, 200, 200),
                new Converter(-3.0, -1.0, -3.0, -1.0, 200, 200),
                new Converter(1.0, 3.0, 1.0, 3.0, 200, 200)
        };
    }

    private ArrayList<ArrayList<Double>> getExpectedCrt() {
        var crt = new ArrayList<ArrayList<Double>>();

        crt.add(new ArrayList<>(List.of(-1.0, -0.5, 0.0, 0.5, 1.0)));
        crt.add(new ArrayList<>(List.of(-3.0, -2.5, -2.0, -1.5, -1.0)));
        crt.add(new ArrayList<>(List.of(1.0, 1.5, 2.0, 2.5, 3.0)));

        return crt;
    }

    private ArrayList<Integer> getExpectedScr(){
        return new ArrayList<>(List.of(0, 50, 100, 150, 200));
    }

    @Test
    void xScr2Crt() {
        var converters = getConverters();
        
        var expectedCrt = getExpectedCrt();
        var expectedScr = getExpectedScr();
        
        for (int i = 0; i < converters.length; i++) {
            var row = new ArrayList<Double>();
            for (var src : expectedScr) {
                row.add(converters[i].xScr2Crt(src));
            }
            Assertions.assertEquals(expectedCrt.get(i), row);
        }
    }

    @Test
    void yScr2Crt() {
        var converters = getConverters();

        var expectedCrt = getExpectedCrt();
        var expectedScr = getExpectedScr();

        for (int i = 0; i < converters.length; i++) {
            var row = new ArrayList<Double>();
            for (var src : expectedScr) {
                row.add(converters[i].yScr2Crt(src));
            }
            
            // Ординаты у декартовой и экранной систем координат разнонаправленны
            Collections.reverse(expectedCrt.get(i));
            Assertions.assertEquals(expectedCrt.get(i), row);
        }
    }

    @Test
    void xCrt2Scr() {
        var converters = getConverters();

        var expectedCrt = getExpectedCrt();
        var expectedScr = getExpectedScr();

        for (int i = 0; i < converters.length; i++) {
                var row = new ArrayList<Integer>();
                for (var crt : expectedCrt.get(i)) {
                    row.add(converters[i].xCrt2Scr(crt));
                }
                Assertions.assertEquals(expectedScr, row);
            }
        }

    @Test
    void yCrt2Scr() {
        var converters = getConverters();

        var expectedCrt = getExpectedCrt();
        var expectedScr = getExpectedScr();
        
        for (int i = 0; i < converters.length; i++) {
            var row = new ArrayList<Integer>();
            for (var crt : expectedCrt.get(i)) {
                row.add(converters[i].yCrt2Scr(crt));
            }

            // Ординаты у декартовой и экранной систем координат разнонаправленны
            Collections.reverse(row);
            Assertions.assertEquals(expectedScr, row);
        }
    }
}