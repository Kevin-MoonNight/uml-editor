package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import modes.CreateMode;
import modes.SelectMode;
import objects.Boundary;
import objects.OvalObject;
import objects.RectObject;
import utils.BoundaryUtil;
import utils.DrawerUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    private Point lastMousePosition;
    private boolean isDragging = false;

    public Canvas() {
        setup();
    }

    private void setup() {
        setLayout(null);
        setBackground(Color.lightGray);

        CanvasManager.getInstance().setCanvas(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePosition = e.getPoint();

                if (UMLManager.getInstance().getMode() instanceof CreateMode) {
                    var createMode = (CreateMode) UMLManager.getInstance().getMode();
                    createMode.setBoundary(new Boundary(e.getX(), e.getY(), 100, 100));
                    createMode.handle();
                    return;
                }

                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setOrigin(e.getPoint());
                }

                var obj = BoundaryUtil.getObjectsAtPoint(UMLManager.getInstance().getObjects(), e.getPoint());
                var selectedObjects = UMLManager.getInstance().getSelectedObjects();

                if (obj == null) {
                    selectedObjects.clear();
                    update();
                } else if (!selectedObjects.contains(obj)) {
                    UMLManager.getInstance().removeObject(obj);
                    UMLManager.getInstance().addObject(obj);
                    UMLManager.getInstance().setSelectedObjects(new java.util.ArrayList<>(java.util.List.of(obj)));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();

                    if (isDragging) {
                        isDragging = false;
                    } else {
                        selectMode.setDestination(e.getPoint());
                    }

                    selectMode.handle();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!(UMLManager.getInstance().getMode() instanceof SelectMode)) {
                    return;
                }

                isDragging = true;
                var selectedObjects = UMLManager.getInstance().getSelectedObjects();
                var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                if (selectedObjects.size() == 0) {
                    selectMode.setDestination(e.getPoint());
                    update();
                    return;
                }

                selectMode.setOrigin(null);
                selectMode.setDestination(null);

                // 計算偏移量
                int dx = e.getX() - lastMousePosition.x;
                int dy = e.getY() - lastMousePosition.y;

                for (var object : selectedObjects) {
                    var boundary = object.getBoundary();
                    boundary.setX(boundary.getX() + dx);
                    boundary.setY(boundary.getY() + dy);
                }

                lastMousePosition = e.getPoint();
                update();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var objects = UMLManager.getInstance().getObjects();
        var selectedObjects = UMLManager.getInstance().getSelectedObjects();
        clear(g);

        // Draw the objects
        for (var object : objects) {
            boolean isSelected = selectedObjects.contains(object);

            if (object instanceof RectObject) {
                DrawerUtil.drawRect(g, object.getBoundary(), isSelected);
            } else if (object instanceof OvalObject) {
                DrawerUtil.drawOval(g, object.getBoundary(), isSelected);
            }
        }

        // Draw the select box
        if (UMLManager.getInstance().getMode() instanceof SelectMode) {
            var selectMode = (SelectMode) UMLManager.getInstance().getMode();
            var origin = selectMode.getOrigin();
            var destination = selectMode.getDestination();

            if (origin != null && destination != null) {
                var boundary = new Boundary(
                        Math.min(origin.x, destination.x),
                        Math.min(origin.y, destination.y),
                        Math.abs(destination.x - origin.x),
                        Math.abs(destination.y - origin.y));
                DrawerUtil.drawSelectBox(g, boundary);
            }
        }
    }

    private void clear(Graphics g) {
        g.setColor(Color.lightGray);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void update() {
        repaint();
    }
}
