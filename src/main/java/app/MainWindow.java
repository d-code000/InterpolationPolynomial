package main.java.app;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Построение графика полинома");
        int width = 800;
        int height = 800;
        setSize(width + 20, height + 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        var drawPanel = new DrawPanel(width, height);
        
        add(drawPanel);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
