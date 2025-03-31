package main.java.converter;

public class Border {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public Border(double borderMinX, double borderMaxX, double borderMinY, double borderMaxY) {
        xMin = borderMinX;
        yMin = borderMinY;
        
        setMaxX(borderMaxX);
        setMaxY(borderMaxY);
    }

    public double getMinX() {
        return xMin;
    }

    public void setMinX(double xMin) {
        if (xMin < xMax){
            this.xMin = xMin;
        }
        else {
            throw new IllegalArgumentException("x_min >= x_max");
        }
    }

    public double getMaxX() {
        return xMax;
    }

    public void setMaxX(double xMax) {
        if (xMax > xMin){
            this.xMax = xMax;
        }
        else {
            throw new IllegalArgumentException("x_max <= x_min");
        }
    }

    public double getMinY() {
        return yMin;
    }

    public void setMinY(double yMin) {
        if (yMin < yMax){
            this.yMin = yMin;
        }
        else {
            throw new IllegalArgumentException("y_min >= y_max");
        }
    }

    public double getMaxY() {
        return yMax;
    }

    public void setMaxY(double yMax) {
        if (yMax > yMin){
            this.yMax = yMax;
        }
        else {
            throw new IllegalArgumentException("y_max <= y_min");
        }
    }
}
