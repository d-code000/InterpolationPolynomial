package app;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPainter extends JPanel implements Painter {
    private Dimension size;

    public AbstractPainter(Dimension size) {
        setSize(size);
    }

    public AbstractPainter(int width, int height) {
        setSize(width, height);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public void setSize(int width, int height) {
        this.size = new Dimension(width, height);
    }
}
