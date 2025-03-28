package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import forms.Canvas;
import modes.Mode;

public class CanvasManager implements ModeChangeListener {
    private Canvas canvas;
    private MouseAdapter defaultTrigger = getDefaultTrigger();

    private CanvasManager() {
    }

    private static class Holder {
        private static final CanvasManager INSTANCE = new CanvasManager();
    }

    public static CanvasManager getInstance() {
        return Holder.INSTANCE;
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

    @Override
    public void onModeChanged(Mode oldMode, Mode newMode) {
        registerTrigger(newMode.getTrigger());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;

        addTrigger(defaultTrigger);
    }
}
