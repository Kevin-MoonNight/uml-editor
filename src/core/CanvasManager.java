package core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import drawers.Drawer;
import drawers.LabelDrawer;
import drawers.LinkDrawer;
import drawers.ObjectDrawer;
import forms.Canvas;
import utils.DrawerUtil;

public class CanvasManager {
    private static CanvasManager instance;

    private static final Drawer[] DEFAULT_DRAWERS = new Drawer[] {
            new ObjectDrawer(),
            new LinkDrawer(),
            new LabelDrawer(),
    };
    private List<Drawer> drawers = new ArrayList<>(Arrays.asList(DEFAULT_DRAWERS));

    private Canvas canvas;

    private MouseAdapter defaultTrigger = getDefaultTrigger();

    private CanvasManager() {
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

    public void registerCustomDrawer(Drawer drawer) {
        drawers.clear();

        drawers.addAll(Arrays.asList(DEFAULT_DRAWERS));

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

        drawers.forEach(drawer -> drawer.draw(g, canvas));
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;

        addTrigger(defaultTrigger);
    }
}
