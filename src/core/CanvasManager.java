package core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import drawers.Drawable;
import drawers.DrawerFactory;
import forms.Canvas;
import utils.DrawerUtil;

public class CanvasManager {
    private final DrawerFactory drawerFactory = new DrawerFactory(UMLManager.getInstance(), this);
    private final List<Drawable> DEFAULT_DRAWERS = drawerFactory.createDefaultDrawers();
    private List<Drawable> drawers = new ArrayList<>(DEFAULT_DRAWERS);

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

    public void registerCustomDrawer(Drawable drawer) {
        drawers.clear();

        drawers.addAll(DEFAULT_DRAWERS);

        if (drawer == null) {
            return;
        }

        drawers.add(drawer);
    }

    public void render(Graphics g) {
        if (canvas == null) {
            return;
        }

        DrawerUtil.clear(canvas, g);

        drawers.forEach(drawer -> drawer.draw(g));
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;

        addTrigger(defaultTrigger);
    }
}
