package app;

import converter.Converter;

import java.awt.*;

public class CartesianPainter extends AbstractPainter implements Painter {
    
    public CartesianPainter(Dimension size, Converter converter) {
        super(size, converter);
    }
    
    public CartesianPainter(int width, int height, Converter converter) {
        super(width, height, converter);
    }
    
    public void paint(Graphics graphics) {
        graphics.drawLine(converter.xCrt2Scr(-5.0), converter.yCrt2Scr(0.0), converter.xCrt2Scr(5.0), converter.yCrt2Scr(0.0));
        graphics.drawLine(converter.xCrt2Scr(0.0), converter.yCrt2Scr(-5.0), converter.xCrt2Scr(0.0), converter.yCrt2Scr(5.0));
        
        for (double i = -5.0; i <= 5.0; i += 0.1) {
            double size;
            double epsilon = 1e-6;
            
            if (Math.abs(i) < epsilon) {
                continue;
            }
            else if (Math.abs(i - Math.round(i)) < epsilon) {
                graphics.setColor(Color.red);
                size = 0.1;
            }
            else if (Math.abs(Math.abs(i) % 1 - 0.5) < epsilon) {
                graphics.setColor(Color.blue);
                size = 0.07;
            }
            else {
                graphics.setColor(Color.black);
                size = 0.05;
            }
            graphics.drawLine(converter.xCrt2Scr(i), converter.yCrt2Scr(-size), converter.xCrt2Scr(i), converter.yCrt2Scr(size));
            graphics.drawLine(converter.xCrt2Scr(-size), converter.yCrt2Scr(i), converter.xCrt2Scr(size), converter.yCrt2Scr(i));
        }
    }
}