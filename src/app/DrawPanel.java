package app;

import converter.Converter;
import polynomial.InterpolatingPolynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class DrawPanel extends JPanel {
    private final Converter converter;
    private final InterpolatingPolynomial polynomial;
    private final CartesianPainter cartesianPainter;
    private final FunctionPainter functionPainter;
    
    public DrawPanel(int width, int height) {
        setSize(width, height);
        converter = new Converter(-5.0, 5.0, -5.0, 5.0, width, height);
        polynomial = new InterpolatingPolynomial();
        cartesianPainter = new CartesianPainter(width, height, converter);
        functionPainter = new FunctionPainter(width, height, converter, polynomial);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                polynomial.addPoint(new Point2D.Double(
                        converter.xScr2Crt(e.getX()), 
                        converter.yScr2Crt(e.getY())
                ));
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
