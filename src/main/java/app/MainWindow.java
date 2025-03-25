package main.java.app;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private int width = 600;
    private int height = 600;
    
    private Border border = new Border(-5.0, 5.0, -5.0, 5.0);
    
    public MainWindow() {
        setTitle("Построение графика полинома");
        setSize(width + 20, height + 40);
        setResizable(false);
        
        var drawPanel = new DrawPanel(width - 20, height - 20, border);
        drawPanel.functionPainter.functionColor = Color.blue;
        drawPanel.functionPainter.pointsColor = Color.green;
        
        add(drawPanel);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
