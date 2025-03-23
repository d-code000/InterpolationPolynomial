package main.java.app;

public class Border {
    public final double xMin;
    public final double xMax;
    public final double yMin;
    public final double yMax;

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
