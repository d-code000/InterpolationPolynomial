package com.disha.app.polynomial.interpolation;

import com.disha.converter.Converter;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel drawPanel;
    private final Converter converter;

    public MainWindow() {
        int width = 600;
        int height = 600;

        double xMin = -5.0;
        double xMax = 5.0;
        double yMin = -5.0;
        double yMax = 5.0;
        
        setTitle("Построение графика полинома");
        setSize(width + 20, height + 120);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        converter = new Converter(xMin, xMax, yMin, yMax, width, height);
        drawPanel = new DrawPanel(width, height, converter);
        drawPanel.functionPainter.functionColor = Color.blue;
        drawPanel.functionPainter.pointsColor = Color.green;

        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel xMinLabel = new JLabel("X Min:");
        JLabel xMaxLabel = new JLabel("X Max:");
        JLabel yMinLabel = new JLabel("Y Min:");
        JLabel yMaxLabel = new JLabel("Y Max:");

        JSpinner xMinSpinner = new JSpinner(new SpinnerNumberModel(xMin, -100.0, 100.0, 0.5));
        xMinSpinner.addChangeListener(_ -> {
            converter.border.setMinX((Double) xMinSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner xMaxSpinner = new JSpinner(new SpinnerNumberModel(xMax, -100.0, 100.0, 0.5));
        xMaxSpinner.addChangeListener(_ -> {
            converter.border.setMaxX((Double) xMaxSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner yMinSpinner = new JSpinner(new SpinnerNumberModel(yMin, -100.0, 100.0, 0.5));
        yMinSpinner.addChangeListener(_ -> {
            converter.border.setMinY((Double) yMinSpinner.getValue());
            drawPanel.repaint();
        });

        JSpinner yMaxSpinner = new JSpinner(new SpinnerNumberModel(yMax, -100.0, 100.0, 0.5));
        yMaxSpinner.addChangeListener(_ -> {
            converter.border.setMaxY((Double) yMaxSpinner.getValue());
            drawPanel.repaint();
        });

        JCheckBox showPointsCheck = new JCheckBox("Отображать точки", true);
        JCheckBox showFunctionCheck = new JCheckBox("Отображать график функции", true);

        showPointsCheck.addActionListener(_ -> {
            drawPanel.functionPainter.drawPoints = showPointsCheck.isSelected();
            drawPanel.repaint();
        });

        showFunctionCheck.addActionListener(_ -> {
            drawPanel.functionPainter.drawFunction = showFunctionCheck.isSelected();
            drawPanel.repaint();
        });

        JButton pointsColorButton = new JButton();
        pointsColorButton.setBackground(Color.green);
        pointsColorButton.setPreferredSize(new Dimension(20, 20));
        pointsColorButton.addActionListener(_ -> {
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
        functionColorButton.addActionListener(_ -> {
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
        gbc.gridx = 4;
        controlPanel.add(showPointsCheck, gbc);
        gbc.gridx = 5;
        controlPanel.add(pointsColorButton, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        controlPanel.add(yMinLabel, gbc);
        gbc.gridx = 1;
        controlPanel.add(yMinSpinner, gbc);
        gbc.gridx = 2;
        controlPanel.add(yMaxLabel, gbc);
        gbc.gridx = 3;
        controlPanel.add(yMaxSpinner, gbc);
        gbc.gridx = 4;
        controlPanel.add(showFunctionCheck, gbc);
        gbc.gridx = 5;
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
