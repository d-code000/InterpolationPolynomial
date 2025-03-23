package main.java.converter;

public class Converter {
    private final Double borderMinX;
    private final Double borderMaxX;
    private final Double borderMinY;
    private final Double borderMaxY;
    
    private Integer widthPixels = 200;
    private Integer heightPixels = 200;
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY) {
        if (borderMinX <= borderMaxX) {
            this.borderMinX = borderMinX;
            this.borderMaxX = borderMaxX;
        }
        else {
            throw new IllegalArgumentException("borderMinX > borderMaxX");
        }
        if (borderMinY <= borderMaxY) {
            this.borderMinY = borderMinY;
            this.borderMaxY = borderMaxY;
        }
        else {
            throw new IllegalArgumentException("borderMinY > borderMaxY");
        }
    }
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY, Integer widthPixels, Integer heightPixels) {
        this(borderMinX, borderMaxX, borderMinY, borderMaxY);
        if (widthPixels >= 0 && heightPixels >= 0) {
            this.widthPixels = widthPixels;
            this.heightPixels = heightPixels;
        }
        else {
            throw new IllegalArgumentException("Ширина и высота должны быть >= 0px");
        }
    }
    
    private Double getWidthPixelsDensity() {
        return widthPixels / (borderMaxX - borderMinX);
    }
    
    private Double getHeightPixelsDensity() {
        return heightPixels / (borderMaxY - borderMinY);
    }
    
    public Double xScr2Crt(Integer xPixels){
        if (xPixels < 0 || xPixels > heightPixels) {
            return null;
        }
        return borderMinX + (xPixels / getWidthPixelsDensity());
    }
    
    public Double yScr2Crt(Integer yPixels){
        if (yPixels < 0 || yPixels > widthPixels) {
            return null;
        }
        return borderMaxY - (yPixels / getHeightPixelsDensity());
    }
    
    public Integer xCrt2Scr(Double x){
        if (x < borderMinX || x > borderMaxX) {
            return null;
        }
        return (int) Math.ceil((Math.abs(x - borderMinX)) * getWidthPixelsDensity());
    }
    
    public Integer yCrt2Scr(Double y) {
        if (y < borderMinY || y > borderMaxY) {
            return null;
        }
        return (int) Math.ceil(Math.abs(borderMaxY - y) * getHeightPixelsDensity());
    }
}
