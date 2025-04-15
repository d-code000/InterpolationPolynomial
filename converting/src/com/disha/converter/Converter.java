package com.disha.converter;

public class Converter {
    
    public Border border;

    private int widthPixels = 0;
    private int heightPixels = 0;
    
    public int getWidthPixels(){
        return widthPixels;
    }
    
    public int getHeightPixels(){
        return heightPixels;
    }

    public void setWidthPixels(int widthPixels) {
        if (widthPixels < 0) {
            throw new IllegalArgumentException("Ширина должна быть неотрицательным числом");
        }
        this.widthPixels = widthPixels;
    }
    
    public void setHeightPixels(int heightPixels) {
        if (heightPixels < 0) {
            throw new IllegalArgumentException("Высота должна быть неотрицательным числом");
        }
        this.heightPixels = heightPixels;
    }

    public Converter(double borderMinX, double borderMaxX, double borderMinY, double borderMaxY) {
        this.border = new Border(borderMinX, borderMaxX, borderMinY, borderMaxY);
    }
    
    public Converter(double borderMinX, double borderMaxX, double borderMinY, double borderMaxY, int widthPixels, int heightPixels) {
        this(borderMinX, borderMaxX, borderMinY, borderMaxY);
        setWidthPixels(widthPixels);
        setHeightPixels(heightPixels);
    }
    
    private double getWidthPixelsDensity() {
        return widthPixels / (border.getMaxX() - border.getMinX());
    }
    
    private double getHeightPixelsDensity() {
        return heightPixels / (border.getMaxY() - border.getMinY());
    }
    
    public double xScr2Crt(int xPixels){
        return border.getMinX() + (xPixels / getWidthPixelsDensity());
    }
    
    public double yScr2Crt(int yPixels){
        return border.getMaxY() - (yPixels / getHeightPixelsDensity());
    }
    
    public int xCrt2Scr(double x){
        return (int) Math.ceil((x - border.getMinX()) * getWidthPixelsDensity());
    }
    
    public int yCrt2Scr(double y) {
        return (int) Math.ceil((border.getMaxY() - y) * getHeightPixelsDensity());
    }

    public double xScr2CrtRatio(int xPixels) {
        return xPixels / getWidthPixelsDensity();
    }
    
    public double yScr2CrtRatio(int yPixels) {
        return yPixels / getHeightPixelsDensity();
    }
}
