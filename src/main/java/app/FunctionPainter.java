package main.java.app;

import main.java.converter.Converter;
import main.java.polynomial.InterpolatingPolynomial;

import java.awt.*;
import java.awt.geom.Point2D;

public class FunctionPainter extends AbstractPainter implements Painter {
    private final InterpolatingPolynomial polynomial;
    public Boolean drawFunction = true;
    public Boolean drawPoints = true;
    public Color functionColor = Color.red;
    public Color pointsColor = Color.gray;
    
    public FunctionPainter(int width, int height, Converter converter, InterpolatingPolynomial polynomial) {
        super(width, height, converter);
        this.polynomial = polynomial;
    }
    
    public void paint(Graphics graphics) {
        
        if (drawFunction) {
            var pointSize = 2;
            graphics.setColor(functionColor);

            var step = Math.abs(converter.border.getMaxX() - converter.border.getMinX()) / 10e4;
            for (double x = converter.border.getMinX(); x <= converter.border.getMaxX(); x += step) {
                var y = polynomial.calc(x);
                graphics.fillOval(
                        converter.xCrt2Scr(x) - pointSize / 2,
                        converter.yCrt2Scr(y) - pointSize / 2,
                        pointSize, pointSize
                );
            }
        }
        
        if (drawPoints) {
            var pointSize = 10;
            graphics.setColor(pointsColor);

            for (Point2D point : polynomial.getPoints()) {
                graphics.fillOval(
                        converter.xCrt2Scr(point.getX()) - pointSize / 2,
                        converter.yCrt2Scr(point.getY()) - pointSize / 2,
                        pointSize, pointSize
                );
            }
        }
    }
}
