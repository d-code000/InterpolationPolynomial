public class Converter {
    private Double borderMinX;
    private Double borderMaxX;
    private Double borderMinY;
    private Double borderMaxY;
    
    private Integer widthPixels = 200;
    private Integer heightPixels = 200;
    
    private Double stepX = 0.1;
    private Double stepY = 0.1;
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY) {
        this.borderMinX = borderMinX;
        this.borderMaxX = borderMaxX;
        this.borderMinY = borderMinY;
        this.borderMaxY = borderMaxY;
        
        stepX = (borderMaxX - borderMinX) / 100;
        stepY = (borderMaxY - borderMinY) / 100;
    }
    
    public Converter(Double borderMinX, Double borderMaxX, Double borderMinY, Double borderMaxY, Integer widthPixels, Integer heightPixels) {
        this.borderMinX = borderMinX;
        this.borderMaxX = borderMaxX;
        this.borderMinY = borderMinY;
        this.borderMaxY = borderMaxY;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
    }
    
    private Integer getWidthPixelsDensity() {
        throw new UnsupportedOperationException();
    }
    
    private Integer getHeightPixelsDensity() {
        throw new UnsupportedOperationException();
    }
    
    public Double xScr2Crt(){
        throw new UnsupportedOperationException();
    }
    
    public Double yScr2Crt(){
        throw new UnsupportedOperationException();
    }
    
    public Integer xCrt2Scr(){
        throw new UnsupportedOperationException();
    }
    
    public Integer yCrt2Scr(){
        throw new UnsupportedOperationException();
    }
    
    
}
