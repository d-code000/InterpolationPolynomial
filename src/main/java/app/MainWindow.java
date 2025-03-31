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
        setSize(width + 20, height + 150);
//        setResizable(false);

        border = new Border(xMin, xMax, yMin, yMax);
        drawPanel = new DrawPanel(width, height, border);
        drawPanel.functionPainter.functionColor = Color.blue;
        drawPanel.functionPainter.pointsColor = Color.green;

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel xMinLabel = new JLabel("X Min:");
        SpinnerNumberModel xMinModel = new SpinnerNumberModel(xMin, -100.0, 100.0, 0.5);
        JSpinner xMinSpinner = new JSpinner(xMinModel);
        xMinSpinner.addChangeListener(e -> {
            border.setMinX((Double) xMinSpinner.getValue());
            drawPanel.repaint();
        });

        JLabel xMaxLabel = new JLabel("X Max:");
        SpinnerNumberModel xMaxModel = new SpinnerNumberModel(xMax, -100.0, 100.0, 0.5);
        JSpinner xMaxSpinner = new JSpinner(xMaxModel);
        xMaxSpinner.addChangeListener(e -> {
            border.setMaxX((Double) xMaxSpinner.getValue());
            drawPanel.repaint();
        });

        JLabel yMinLabel = new JLabel("Y Min:");
        SpinnerNumberModel yMinModel = new SpinnerNumberModel(yMin, -100.0, 100.0, 0.5);
        JSpinner yMinSpinner = new JSpinner(yMinModel);
        yMinSpinner.addChangeListener(e -> {
            border.setMinY((Double) yMinSpinner.getValue());
            drawPanel.repaint();
        });

        JLabel yMaxLabel = new JLabel("Y Max:");
        SpinnerNumberModel yMaxModel = new SpinnerNumberModel(yMax, -100.0, 100.0, 0.5);
        JSpinner yMaxSpinner = new JSpinner(yMaxModel);
        yMaxSpinner.addChangeListener(e -> {
            border.setMaxY((Double) yMaxSpinner.getValue());
            drawPanel.repaint();
        });
        
        JCheckBox showPointsCheck = new JCheckBox("Отображать точки", true);
        JCheckBox showFunctionCheck = new JCheckBox("Отображать график функции", true);
        JCheckBox showDerivativeCheck = new JCheckBox("Отображать производную", false);

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

        JButton derivativeColorButton = new JButton();
        derivativeColorButton.setBackground(Color.pink);
        derivativeColorButton.setPreferredSize(new Dimension(20, 20));
        derivativeColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Выберите цвет производной", derivativeColorButton.getBackground());
            if (color != null) {
                derivativeColorButton.setBackground(color);
//                drawPanel.functionPainter.derivativeColor = color;
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

        controlPanel.add(showPointsCheck);
        controlPanel.add(pointsColorButton);
        controlPanel.add(showFunctionCheck);
        controlPanel.add(functionColorButton);
        controlPanel.add(showDerivativeCheck);
        controlPanel.add(derivativeColorButton);

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
