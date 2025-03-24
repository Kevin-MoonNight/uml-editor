package core;

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
}
