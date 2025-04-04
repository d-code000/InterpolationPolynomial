package test.java.converter;

import main.java.converter.Converter;

import java.util.ArrayList;

public class ConverterHelper {
    private final Converter converter;
    private final ArrayList<ArrayList<Double>> crtArray;
    private final ArrayList<ArrayList<Integer>> scrArray;
    
    public ConverterHelper(Converter converter, ArrayList<ArrayList<Double>> crtArray, ArrayList<ArrayList<Integer>> scrArray){
        this.converter = converter;
        this.crtArray = crtArray;
        this.scrArray = scrArray;
    }
    
    public Converter getConverter(){
        return converter;
    }
    
    public ArrayList<ArrayList<Double>> getExpectedCrt(){
        return crtArray;
    }
    
    public ArrayList<ArrayList<Integer>> getExpectedScr(){
        return scrArray;
    }
}
