package main.java.app;

import main.java.converter.Converter;

import java.awt.*;

public class CartesianPainter extends AbstractPainter implements Painter {
    private final Border border;

    public CartesianPainter(int width, int height, Converter converter, Border border) {
        super(width, height, converter);
        this.border = border;
    }
    
    public void paint(Graphics graphics) {
        graphics.drawLine(converter.xCrt2Scr(border.xMin), converter.yCrt2Scr(0.0), converter.xCrt2Scr(border.xMax), converter.yCrt2Scr(0.0));
        graphics.drawLine(converter.xCrt2Scr(0.0), converter.yCrt2Scr(border.yMin), converter.xCrt2Scr(0.0), converter.yCrt2Scr(border.yMax));

        double epsilon = 1e-6;
        
        for (double i = border.xMin; i <= border.xMax; i += 0.1) {
            double size;
            if (Math.abs(i) < epsilon) {
                continue;
            }
            else if (Math.abs(i - Math.round(i)) < epsilon) {
                graphics.setColor(Color.red);
                size = converter.yScr2CrtRatio(8);
            }
            else if (Math.abs(Math.abs(i) % 1 - 0.5) < epsilon) {
                graphics.setColor(Color.blue);
                size = converter.yScr2CrtRatio(5);
            }
            else {
                graphics.setColor(Color.black);
                size = converter.yScr2CrtRatio(3);
            }
            graphics.drawLine(converter.xCrt2Scr(i), converter.yCrt2Scr(-size), converter.xCrt2Scr(i), converter.yCrt2Scr(size));
        }

        for (double i = border.yMin; i <= border.yMax; i += 0.1) {
            double size;

            if (Math.abs(i) < epsilon) {
                continue;
            }
            else if (Math.abs(i - Math.round(i)) < epsilon) {
                graphics.setColor(Color.red);
                size = converter.xScr2CrtRatio(8);
            }
            else if (Math.abs(Math.abs(i) % 1 - 0.5) < epsilon) {
                graphics.setColor(Color.blue);
                size = converter.xScr2CrtRatio(5);
            }
            else {
                graphics.setColor(Color.black);
                size = converter.xScr2CrtRatio(3);
            }
            graphics.drawLine(converter.xCrt2Scr(-size), converter.yCrt2Scr(i), converter.xCrt2Scr(size), converter.yCrt2Scr(i));
        }
    }
}