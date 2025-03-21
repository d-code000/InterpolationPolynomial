package app;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private int x;
    private int y;
    private Color color;
    
    public DrawPanel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        graphics.setColor(color);
        graphics.drawLine(0, getHeight() / 2 + y, getWidth(), getHeight() / 2 + y);
        graphics.drawLine(getWidth() / 2 + x, 0, getWidth() / 2 + x, getHeight());
    }
}
