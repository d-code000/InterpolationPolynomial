package com.disha.app.polynomial.interpolation;

import java.awt.*;

public interface Painter {
    Dimension getSize();
    void setSize(Dimension size);
    void setSize(int width, int height);
    void paint(Graphics graphics);
}
