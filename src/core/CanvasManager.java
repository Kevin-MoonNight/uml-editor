package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import forms.Canvas;

public class CanvasManager {
    private static CanvasManager instance;
    private Canvas canvas;
    private MouseAdapter defaultListener;

    private CanvasManager() {
        defaultListener = getDefaultListener();
    }

    public static CanvasManager getInstance() {
        if (instance == null) {
            instance = new CanvasManager();
        }
        return instance;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;

        // Add default listener
        canvas.addMouseListener(defaultListener);
        canvas.addMouseMotionListener(defaultListener);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void update() {
        canvas.repaint();
    }

    public void registerTrigger(MouseAdapter trigger) {
        clearMouseListeners();

        canvas.addMouseListener(trigger);
        canvas.addMouseMotionListener(trigger);
    }

    public void clearMouseListeners() {
        for (MouseListener listener : canvas.getMouseListeners()) {
            // Remove all listeners except the default listener
            if (listener != defaultListener) {
                canvas.removeMouseListener(listener);
            }
        }

        for (MouseMotionListener listener : canvas.getMouseMotionListeners()) {
            // Remove all listeners except the default listener
            if (listener != defaultListener) {
                canvas.removeMouseMotionListener(listener);
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
        };
    }
}
