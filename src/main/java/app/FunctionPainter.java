package main.java.app;

import main.java.converter.Converter;
import main.java.polynomial.InterpolatingPolynomial;

import java.awt.*;
import java.awt.geom.Point2D;

public class FunctionPainter extends AbstractPainter implements Painter {
    private final InterpolatingPolynomial polynomial;
    private final Border border;

    public FunctionPainter(Dimension size, Converter converter, InterpolatingPolynomial polynomial, Border border) {
        super(size, converter);
        this.polynomial = polynomial;
        this.border = border;
    }
    
    public FunctionPainter(int width, int height, Converter converter, InterpolatingPolynomial polynomial, Border border) {
        super(width, height, converter);
        this.polynomial = polynomial;
        this.border = border;
    }
    
    public void paint(Graphics graphics) {
        var step = Math.abs(border.xMax - border.xMin) / 10e4;
        
        var pointSize = 2;
        graphics.setColor(Color.RED);
        
        for (double x = border.xMin; x <= border.xMax; x += step) {
            var y = polynomial.calc(x);
            if (converter.checkPointCrt2Scr(x, y)) {
                graphics.fillOval(
                        converter.xCrt2Scr(x) - pointSize / 2, 
                        converter.yCrt2Scr(y) - pointSize / 2, 
                        pointSize, pointSize
                );
            }
        }
        
        pointSize = 10;
        graphics.setColor(Color.GRAY);
        
        for (Point2D point : polynomial.getPoints()) {
            if (converter.checkPointCrt2Scr(point.getX(), point.getY())) {
                graphics.fillOval(
                        converter.xCrt2Scr(point.getX()) - pointSize / 2,
                        converter.yCrt2Scr(point.getY()) - pointSize / 2,
                        pointSize, pointSize
                );
            }
        }
    }
}
