package app;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Построение графика полинома");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        var drawPanel1 = new DrawPanel(0, 0, Color.BLACK);
        drawPanel1.setBounds(0, 0, 400, 400);
        var drawPanel2 = new DrawPanel(15, 15, Color.CYAN);
        drawPanel2.setBounds(0, 0, 300, 300);
        
        add(drawPanel1);
        add(drawPanel2);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
