package converter;

public class Converter {
    private final Double borderMinX;
    private final Double borderMaxX;
    private final Double borderMinY;
    private final Double borderMaxY;
    
    private Integer widthPixels = 200;
    private Integer heightPixels = 200;
    
    private final Double stepX = 0.1;
    private final Double stepY = 0.1;
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY) {
        this.borderMinX = borderMinX;
        this.borderMaxX = borderMaxX;
        this.borderMinY = borderMinY;
        this.borderMaxY = borderMaxY;
    }
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY, Integer widthPixels, Integer heightPixels) {
        this(borderMinX, borderMaxX, borderMinY, borderMaxY);
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
    }
    
    private Integer getWidthPixelsDensity() {
        return (int) Math.ceil(widthPixels / ((borderMaxX - borderMinX) / stepX));
    }
    
    private Integer getHeightPixelsDensity() {
        return (int) Math.ceil(heightPixels / ((borderMaxY - borderMinY) / stepY));
    }
    
    public Double xScr2Crt(Integer xPixels){
        return borderMinX + (xPixels / getWidthPixelsDensity() * stepX);
    }
    
    public Double yScr2Crt(Integer yPixels){
        return borderMaxY - (yPixels / getHeightPixelsDensity() * stepY);
    }
    
    public Integer xCrt2Scr(Double x){
        return (int) Math.ceil(((Math.abs(x - borderMinX)) / stepX) * getWidthPixelsDensity());
    }
    
    public Integer yCrt2Scr(Double y){
        return (int) Math.ceil((borderMaxY - y) / stepY) * getHeightPixelsDensity();
    }
}
