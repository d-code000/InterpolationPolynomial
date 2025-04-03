package main.java.converter;

public class Converter {
    
    private final Border border;
    private Integer widthPixels = 200;
    private Integer heightPixels = 200;
    
    public Converter(Border border) {
        this.border = border;
    }

    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY) {
        this.border = new Border(borderMinX, borderMaxX, borderMinY, borderMaxY);
    }
    
    public Converter(Border border, Integer widthPixels, Integer heightPixels){
        this.border = border;
        if (widthPixels >= 0 && heightPixels >= 0) {
            this.widthPixels = widthPixels;
            this.heightPixels = heightPixels;
        }
        else {
            throw new IllegalArgumentException("Ширина и высота должны быть >= 0px");
        }
    }
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY, Integer widthPixels, Integer heightPixels) {
        this(new Border(borderMinX, borderMaxX, borderMinY, borderMaxY), widthPixels, heightPixels);
    }
    
    private Double getWidthPixelsDensity() {
        return widthPixels / (border.getMaxX() - border.getMinX());
    }
    
    private Double getHeightPixelsDensity() {
        return heightPixels / (border.getMaxY() - border.getMinY());
    }
    
    public Double xScr2Crt(Integer xPixels){
        if (xPixels < 0 || xPixels > heightPixels) {
            return null;
        }
        return border.getMinX() + (xPixels / getWidthPixelsDensity());
    }
    
    public Double yScr2Crt(Integer yPixels){
        if (yPixels < 0 || yPixels > widthPixels) {
            return null;
        }
        return border.getMaxY() - (yPixels / getHeightPixelsDensity());
    }
    
    public Integer xCrt2Scr(Double x){
        if (x < border.getMinX() || x > border.getMaxX()) {
            return null;
        }
        return (int) Math.ceil((Math.abs(x - border.getMinX())) * getWidthPixelsDensity());
    }
    
    public Integer yCrt2Scr(Double y) {
        if (y < border.getMinY() || y > border.getMaxY()) {
            return null;
        }
        return (int) Math.ceil(Math.abs(border.getMaxY() - y) * getHeightPixelsDensity());
    }
    
    public boolean checkPointCrt2Scr(Double x, Double y) {
        return (xCrt2Scr(x) != null) && (yCrt2Scr(y) != null);
    }
    
    public boolean checkPointScr2Crt(Integer x, Integer y) {
        return (xScr2Crt(x) != null) && (yScr2Crt(y) != null);
    }

    public Double xScr2CrtRatio(Integer xPixels) {
        return xPixels / getWidthPixelsDensity();
    }
    
    public Double yScr2CrtRatio(Integer yPixels) {
        return yPixels / getHeightPixelsDensity();
    }
}
