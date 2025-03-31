package main.java.app;

import main.java.converter.Border;

import javax.swing.*;
import java.awt.*;

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
        setSize(width + 20, height + 200);
        setResizable(false);

        border = new Border(xMin, xMax, yMin, yMax);
        drawPanel = new DrawPanel(width, height, border);
        drawPanel.functionPainter.functionColor = Color.blue;
        drawPanel.functionPainter.pointsColor = Color.green;

        JPanel controlPanel = new JPanel(new GridBagLayout()); // Используем GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel xMinLabel = new JLabel("X Min:");
        JLabel xMaxLabel = new JLabel("X Max:");
        JLabel yMinLabel = new JLabel("Y Min:");
        JLabel yMaxLabel = new JLabel("Y Max:");

        JSpinner xMinSpinner = new JSpinner(new SpinnerNumberModel(xMin, -100.0, 100.0, 0.5));
        xMinSpinner.addChangeListener(e -> {
            border.setMinX((Double) xMinSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner xMaxSpinner = new JSpinner(new SpinnerNumberModel(xMax, -100.0, 100.0, 0.5));
        xMaxSpinner.addChangeListener(e -> {
            border.setMaxX((Double) xMaxSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner yMinSpinner = new JSpinner(new SpinnerNumberModel(yMin, -100.0, 100.0, 0.5));
        yMinSpinner.addChangeListener(e -> {
            border.setMinY((Double) yMinSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner yMaxSpinner = new JSpinner(new SpinnerNumberModel(yMax, -100.0, 100.0, 0.5));
        yMaxSpinner.addChangeListener(e -> {
            border.setMaxY((Double) yMaxSpinner.getValue());
            drawPanel.repaint();
        });

        JCheckBox showPointsCheck = new JCheckBox("Отображать точки", true);
        JCheckBox showFunctionCheck = new JCheckBox("Отображать график функции", true);

        JButton pointsColorButton = new JButton();
        pointsColorButton.setBackground(Color.green);
        pointsColorButton.setPreferredSize(new Dimension(20, 20));
        pointsColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Выберите цвет точек", pointsColorButton.getBackground());
            if (color != null) {
                pointsColorButton.setBackground(color);
                drawPanel.functionPainter.pointsColor = color;
                drawPanel.repaint();
            }
        });

        JButton functionColorButton = new JButton();
        functionColorButton.setBackground(Color.blue);
        functionColorButton.setPreferredSize(new Dimension(20, 20));
        functionColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Выберите цвет функции", functionColorButton.getBackground());
            if (color != null) {
                functionColorButton.setBackground(color);
                drawPanel.functionPainter.functionColor = color;
                drawPanel.repaint();
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        controlPanel.add(xMinLabel, gbc);
        gbc.gridx = 1;
        controlPanel.add(xMinSpinner, gbc);
        gbc.gridx = 2;
        controlPanel.add(xMaxLabel, gbc);
        gbc.gridx = 3;
        controlPanel.add(xMaxSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        controlPanel.add(yMinLabel, gbc);
        gbc.gridx = 1;
        controlPanel.add(yMinSpinner, gbc);
        gbc.gridx = 2;
        controlPanel.add(yMaxLabel, gbc);
        gbc.gridx = 3;
        controlPanel.add(yMaxSpinner, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        controlPanel.add(showPointsCheck, gbc);
        gbc.gridx = 1;
        controlPanel.add(pointsColorButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        controlPanel.add(showFunctionCheck, gbc);
        gbc.gridx = 1;
        controlPanel.add(functionColorButton, gbc);
        
        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
