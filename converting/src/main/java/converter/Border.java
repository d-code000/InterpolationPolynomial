package main.java.converter;

public class Border {
    public double xMin;
    public double xMax;
    public double yMin;
    public double yMax;

    public Border(double borderMinX, double borderMaxX, double borderMinY, double borderMaxY) {
        if (borderMinX <= borderMaxX) {
            this.xMin = borderMinX;
            this.xMax = borderMaxX;
        }
        else {
            throw new IllegalArgumentException("borderMinX > borderMaxX");
        }
        if (borderMinY <= borderMaxY) {
            this.yMin = borderMinY;
            this.yMax = borderMaxY;
        }
        else {
            throw new IllegalArgumentException("borderMinY > borderMaxY");
        }
    }
}
