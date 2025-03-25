package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import forms.Canvas;

public class CanvasManager {
    private static CanvasManager instance;
    private Canvas canvas;

    private CanvasManager() {
    }

    public static CanvasManager getInstance() {
        if (instance == null) {
            instance = new CanvasManager();
        }
        return instance;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void update() {
        canvas.repaint();
    }

    public void addMouseListener(MouseAdapter adapter) {
        clearMouseListeners();

        canvas.addMouseListener(adapter);
    }

    public void clearMouseListeners() {
        for (MouseListener listener : canvas.getMouseListeners()) {
            // Remove all listeners except the default listener
            if (listener != getDefaultListener()) {
                canvas.removeMouseListener(listener);
            }
        }
    }

    public MouseAdapter getDefaultListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                update();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                update();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                update();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                update();
            }
        };
    }
}
