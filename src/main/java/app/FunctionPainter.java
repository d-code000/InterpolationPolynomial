package main.java.app;

import main.java.converter.Converter;
import main.java.polynomial.InterpolatingPolynomial;

import java.awt.*;
import java.awt.geom.Point2D;

public class FunctionPainter extends AbstractPainter implements Painter {
    private final InterpolatingPolynomial polynomial;

    public FunctionPainter(Dimension size, Converter converter, InterpolatingPolynomial polynomial) {
        super(size, converter);
        this.polynomial = polynomial;
    }
    
    public FunctionPainter(int width, int height, Converter converter, InterpolatingPolynomial polynomial) {
        super(width, height, converter);
        this.polynomial = polynomial;
    }
    
    public void paint(Graphics graphics) {
        
        for (Point2D point : polynomial.getPoints()) {
            var pointSize = 10;
            graphics.setColor(Color.GREEN);
            graphics.fillOval(
                    converter.xCrt2Scr(point.getX()) - pointSize / 2, 
                    converter.yCrt2Scr(point.getY()) - pointSize / 2, 
                    pointSize, pointSize
            );
        }
        
        for (double i = -5.0; i <= 5.0; i += 1e-4) {
            var pointSize = 4;
            graphics.setColor(Color.RED);
            var result = converter.yCrt2Scr(polynomial.calc(i));
            if (result != null) {
                graphics.fillOval(
                        converter.xCrt2Scr(i) - pointSize / 2, 
                        result - pointSize / 2, 
                        pointSize, pointSize
                );
            }
        }
    }
}
