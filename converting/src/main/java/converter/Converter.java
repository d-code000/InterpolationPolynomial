package main.java.converter;

public class Converter {
    
    public final Border border;

    private Integer widthPixels = 200;
    private Integer heightPixels = 200;

    public void setWidthPixels(Integer widthPixels) {
        if (widthPixels < 0) {
            throw new IllegalArgumentException("Ширина должна быть неотрицательным числом");
        }
        this.widthPixels = widthPixels;
    }
    
    public void setHeightPixels(Integer heightPixels) {
        if (heightPixels < 0) {
            throw new IllegalArgumentException("Высота должна быть неотрицательным числом");
        }
        this.heightPixels = heightPixels;
    }

    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY) {
        this.border = new Border(borderMinX, borderMaxX, borderMinY, borderMaxY);
    }
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY, Integer widthPixels, Integer heightPixels) {
        this(borderMinX, borderMaxX, borderMinY, borderMaxY);
        setWidthPixels(widthPixels);
        setHeightPixels(heightPixels);
    }
    
    private Double getWidthPixelsDensity() {
        return widthPixels / (border.getMaxX() - border.getMinX());
    }
    
    private Double getHeightPixelsDensity() {
        return heightPixels / (border.getMaxY() - border.getMinY());
    }
    
    public Double xScr2Crt(Integer xPixels){
        return border.getMinX() + (xPixels / getWidthPixelsDensity());
    }
    
    public Double yScr2Crt(Integer yPixels){
        return border.getMaxY() - (yPixels / getHeightPixelsDensity());
    }
    
    public Integer xCrt2Scr(Double x){
        return (int) Math.ceil((x - border.getMinX()) * getWidthPixelsDensity());
    }
    
    public Integer yCrt2Scr(Double y) {
        return (int) Math.ceil((border.getMaxY() - y) * getHeightPixelsDensity());
    }

    public Double xScr2CrtRatio(Integer xPixels) {
        return xPixels / getWidthPixelsDensity();
    }
    
    public Double yScr2CrtRatio(Integer yPixels) {
        return yPixels / getHeightPixelsDensity();
    }
}
