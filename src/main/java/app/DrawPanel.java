package main.java.app;

import main.java.converter.Converter;
import main.java.polynomial.InterpolatingPolynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class DrawPanel extends JPanel {
    private final Converter converter;
    private final InterpolatingPolynomial polynomial;
    public final CartesianPainter cartesianPainter;
    public final FunctionPainter functionPainter;
    
    public DrawPanel(int width, int height, Border border) {
        setSize(width, height);
        
        // TODO: заменить границы на Border
        converter = new Converter(border.xMin, border.xMax, border.yMin, border.yMax, width, height);
        polynomial = new InterpolatingPolynomial();
        cartesianPainter = new CartesianPainter(width, height, converter, border);
        functionPainter = new FunctionPainter(width, height, converter, polynomial, border);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (converter.checkPointScr2Crt(e.getX(), e.getY())) {
                    polynomial.addPoint(new Point2D.Double(
                            converter.xScr2Crt(e.getX()), 
                            converter.yScr2Crt(e.getY())
                    ));
                }
                repaint();
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        cartesianPainter.paint(graphics);
        functionPainter.paint(graphics);
    }
}
