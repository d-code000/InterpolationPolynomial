package main.java.app;

import main.java.converter.Border;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame {
    private int width = 600;
    private int height = 600;

    private double xMin = -5.0;
    private double xMax = 5.0;
    private double yMin = -5.0;
    private double yMax = 5.0;

    private Border border;
    private DrawPanel drawPanel;

    public MainWindow() {
        setTitle("Построение графика полинома");
        setSize(width + 20, height + 100);
        setResizable(false);

        border = new Border(xMin, xMax, yMin, yMax);
        drawPanel = new DrawPanel(width, height, border);
        drawPanel.functionPainter.functionColor = Color.blue;
        drawPanel.functionPainter.pointsColor = Color.green;
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        JLabel xMinLabel = new JLabel("X Min:");
        SpinnerNumberModel xMinModel = new SpinnerNumberModel(xMin, -100.0, 100.0, 0.5);
        JSpinner xMinSpinner = new JSpinner(xMinModel);
        xMinSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                border.setMinX((Double) xMinSpinner.getValue());
                drawPanel.repaint();
            }
        });
        
        JLabel xMaxLabel = new JLabel("X Max:");
        SpinnerNumberModel xMaxModel = new SpinnerNumberModel(xMax, -100.0, 100.0, 0.5);
        JSpinner xMaxSpinner = new JSpinner(xMaxModel);
        xMaxSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                border.setMaxX((Double) xMaxSpinner.getValue());
                drawPanel.repaint();
            }
        });
        
        JLabel yMinLabel = new JLabel("Y Min:");
        SpinnerNumberModel yMinModel = new SpinnerNumberModel(yMin, -100.0, 100.0, 0.5);
        JSpinner yMinSpinner = new JSpinner(yMinModel);
        yMinSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                border.setMinY((Double) yMinSpinner.getValue());
                drawPanel.repaint();
            }
        });
        
        JLabel yMaxLabel = new JLabel("Y Max:");
        SpinnerNumberModel yMaxModel = new SpinnerNumberModel(yMax, -100.0, 100.0, 0.5);
        JSpinner yMaxSpinner = new JSpinner(yMaxModel);
        yMaxSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                border.setMaxY((Double) yMaxSpinner.getValue());
                drawPanel.repaint();
            }
        });
        
        controlPanel.add(xMinLabel);
        controlPanel.add(xMinSpinner);
        controlPanel.add(xMaxLabel);
        controlPanel.add(xMaxSpinner);
        controlPanel.add(yMinLabel);
        controlPanel.add(yMinSpinner);
        controlPanel.add(yMaxLabel);
        controlPanel.add(yMaxSpinner);
        
        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}