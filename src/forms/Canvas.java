package forms;

import javax.swing.*;

import core.CanvasManager;

import java.awt.*;

public class Canvas extends JPanel {
    private static final CanvasManager canvasManager = CanvasManager.getInstance();

    public Canvas() {
        setup();
    }

    public void setup() {
        setLayout(null);
        setBackground(Color.lightGray);

        canvasManager.setCanvas(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        canvasManager.render(g);
    }
}
