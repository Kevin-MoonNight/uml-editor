package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import forms.Canvas;

public class CanvasManager {
    private static CanvasManager instance;
    private Canvas canvas;
    private MouseAdapter defaultTrigger;

    private CanvasManager() {
        defaultTrigger = getDefaultTrigger();
    }

    public static CanvasManager getInstance() {
        if (instance == null) {
            instance = new CanvasManager();
        }
        return instance;
    }

    public void update() {
        if (canvas == null) {
            return;
        }

        canvas.repaint();
    }

    public void registerTrigger(MouseAdapter trigger) {
        if (canvas == null) {
            return;
        }

        clearTrigger();

        addTrigger(trigger);
    }

    private void addTrigger(MouseAdapter trigger) {
        canvas.addMouseListener(trigger);
        canvas.addMouseMotionListener(trigger);
    }

    private void clearTrigger() {
        Arrays.stream(canvas.getMouseListeners())
                .filter(listener -> listener != defaultTrigger)
                .forEach(canvas::removeMouseListener);

        Arrays.stream(canvas.getMouseMotionListeners())
                .filter(listener -> listener != defaultTrigger)
                .forEach(canvas::removeMouseMotionListener);
    }

    private MouseAdapter getDefaultTrigger() {
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                update();
            }

            public void mouseReleased(MouseEvent e) {
                update();
            }

            public void mouseDragged(MouseEvent e) {
                update();
            }
        };
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;

        addTrigger(defaultTrigger);
    }
}
