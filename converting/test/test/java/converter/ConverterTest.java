package test.java.converter;

import main.java.converter.Border;
import main.java.converter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ConverterTest {
    
    private ArrayList<ConverterHelper> getHelpers(){
        var helpers = new ArrayList<ConverterHelper>();
        
        helpers.add(new ConverterHelper(
                new Converter(-1.0, 1.0, -1.0, 1.0, 200, 200),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(-1.0, -0.5, 0.0, 0.5, 1.0)),
                        new ArrayList<>(List.of(-2.0, -1.5, 1.5, 2.0))
                )),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(0, 50, 100, 150, 200)),
                        new ArrayList<>(List.of(-100, -50, 250, 300))
                ))
        ));
        
        helpers.add(new ConverterHelper(
                new Converter(-3.0, -1.0, -3.0, -1.0, 200, 200),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(-3.0, -2.5, -2.0, -1.5, -1.0)),
                        new ArrayList<>(List.of(-4.0, -3.5, -0.5, 0.0))
                )),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(0, 50, 100, 150, 200)),
                        new ArrayList<>(List.of(-100, -50, 250, 300))
                ))
        ));
        
        helpers.add(new ConverterHelper(
                new Converter(1.0, 3.0, 1.0, 3.0, 200, 200),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(1.0, 1.5, 2.0, 2.5, 3.0)),
                        new ArrayList<>(List.of(0.0, 0.5, 3.5, 4.0))
                )),
                new ArrayList<>(Arrays.asList(
                        new ArrayList<>(List.of(0, 50, 100, 150, 200)),
                        new ArrayList<>(List.of(-100, -50, 250, 300))
                ))
        ));
        
        return helpers;
    }
    
    @Test
    void constructors(){
        new Converter(-1.0, 1.0, -1.0, 1.0);
        new Converter(-3.0, -1.0, -3.0, 1.0, 100,100);
    }

    @Test
    void xScr2Crt() {
        for (ConverterHelper test : getHelpers()){
            var converter = test.getConverter();
            var expectedCrtArrays = test.getExpectedCrt();
            var expectedScrArrays = test.getExpectedScr();
            
            for (int i = 0; i < expectedScrArrays.size(); i++){
                var row = new ArrayList<Double>();
                for (var scr: expectedScrArrays.get(i)){
                    row.add(converter.xScr2Crt(scr));
                }
                
                Assertions.assertArrayEquals(expectedCrtArrays.get(i).toArray(), row.toArray());
            }
        }
    }

    @Test
    void yScr2Crt() {
        for (ConverterHelper test : getHelpers()){
            var converter = test.getConverter();
            var expectedCrtArrays = test.getExpectedCrt();
            var expectedScrArrays = test.getExpectedScr();

            for (int i = 0; i < expectedScrArrays.size(); i++){
                var row = new ArrayList<Double>();
                for (var scr: expectedScrArrays.get(i)){
                    row.add(converter.yScr2Crt(scr));
                }

                Collections.reverse(row);
                Assertions.assertArrayEquals(expectedCrtArrays.get(i).toArray(), row.toArray());
            }
        }
    }
    
    @Test
    void xCrt2Scr() {
        for (ConverterHelper test : getHelpers()){
            var converter = test.getConverter();
            var expectedCrtArrays = test.getExpectedCrt();
            var expectedScrArrays = test.getExpectedScr();

            for (int i = 0; i < expectedCrtArrays.size(); i++){
                var row = new ArrayList<Integer>();
                for (var crt: expectedCrtArrays.get(i)){
                    row.add(converter.xCrt2Scr(crt));
                }

                Assertions.assertArrayEquals(expectedScrArrays.get(i).toArray(), row.toArray());
            }
        }
    }
    
    @Test
    void yCrt2Scr() {
        for (ConverterHelper test : getHelpers()){
            var converter = test.getConverter();
            var expectedCrtArrays = test.getExpectedCrt();
            var expectedScrArrays = test.getExpectedScr();

            for (int i = 0; i < expectedCrtArrays.size(); i++){
                var row = new ArrayList<Integer>();
                for (var crt: expectedCrtArrays.get(i)){
                    row.add(converter.yCrt2Scr(crt));
                }

                Collections.reverse(row);
                Assertions.assertArrayEquals(expectedScrArrays.get(i).toArray(), row.toArray());
            }
        }
    }
    
    @Test
    void negativePixel(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Converter(-1.0, 1.0, -1.0, 1.0, 100, -100));
    }
    
    @Test
    void borderMinMoreBorderMax(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Converter(1.0, -1.0, 1.0, 1.0, 100, 100));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Converter(-1.0, 1.0, 1.0, -1.0, 100, 100));
    }
    
    @Test
    void anyTest(){
        var converter = new Converter(-5.0, 5.0, -5.0, 5.0, 800, 800);
        
        Assertions.assertNotEquals(converter.yCrt2Scr(0.0), converter.yCrt2Scr(0.1));
        Assertions.assertNotEquals(converter.yCrt2Scr(0.0), converter.yCrt2Scr(-0.1));
        
    }
    
    @Test
    void xScr2CrtRatio(){
        var converter = new Converter(-1.0, 1.0, -1.0, 1.0, 200, 200);
        
        Assertions.assertEquals(0.1, converter.xScr2CrtRatio(10));
    }
    
    @Test
    void yScr2CrtRatio(){
        var converter = new Converter(-1.0, 1.0, -1.0, 1.0, 200, 200);

        Assertions.assertEquals(0.1, converter.yScr2CrtRatio(10));
    }
}