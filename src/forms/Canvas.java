package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import modes.CreateMode;
import modes.LinkMode;
import modes.SelectMode;
import objects.Boundary;
import objects.CompositeObject;
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

                if (UMLManager.getInstance().getMode() instanceof LinkMode) {
                    var linkMode = (LinkMode) UMLManager.getInstance().getMode();
                    var sourceObject = BoundaryUtil.getObjectAtPoint(UMLManager.getInstance().getObjects(),
                            e.getPoint());

                    if (sourceObject == null || sourceObject instanceof CompositeObject) {
                        return;
                    }

                    System.out.println("Pressed LinkMode");
                    linkMode.setSource(sourceObject);
                    linkMode.setSourcePoint(e.getPoint());
                    update();
                    return;
                }

                if (UMLManager.getInstance().getMode() instanceof CreateMode) {
                    var createMode = (CreateMode) UMLManager.getInstance().getMode();
                    createMode.setBoundary(new Boundary(e.getX(), e.getY(), 100, 100));
                    createMode.handle();

                    update();
                    return;
                }

                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setOrigin(e.getPoint());
                }

                var obj = BoundaryUtil.getObjectAtPoint(UMLManager.getInstance().getObjects(), e.getPoint());
                var selectedObjects = UMLManager.getInstance().getSelectedObjects();

                if (obj == null) {
                    selectedObjects.clear();
                } else if (!selectedObjects.contains(obj)) {
                    UMLManager.getInstance().removeObject(obj);
                    UMLManager.getInstance().addObject(obj);
                    UMLManager.getInstance().setSelectedObjects(new java.util.ArrayList<>(java.util.List.of(obj)));
                }

                update();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastMousePosition = e.getPoint();

                if (UMLManager.getInstance().getMode() instanceof LinkMode) {
                    var linkMode = (LinkMode) UMLManager.getInstance().getMode();
                    var targetObject = BoundaryUtil.getObjectAtPoint(UMLManager.getInstance().getObjects(),
                            e.getPoint());

                    linkMode.setTarget(targetObject);
                    linkMode.setTargetPoint(e.getPoint());
                    linkMode.handle();
                }

                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();

                    if (isDragging) {
                        isDragging = false;
                    } else {
                        selectMode.setDestination(e.getPoint());
                    }

                    selectMode.handle();
                }

                update();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (UMLManager.getInstance().getMode() instanceof LinkMode) {
                    System.out.println("Dragged LinkMode");
                    var linkMode = (LinkMode) UMLManager.getInstance().getMode();

                    linkMode.setTargetPoint(e.getPoint());
                    update();
                }

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

                BoundaryUtil.moveObjects(selectedObjects, dx, dy);

                var links = UMLManager.getInstance().getLinks();
                links.forEach(link -> {
                    link.updateConnectionPoints();
                });

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
            } else if (object instanceof CompositeObject) {
                DrawerUtil.drawCompositeObject(g, (CompositeObject) object, isSelected);
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

        // Draw the links
        var links = UMLManager.getInstance().getLinks();
        for (var link : links) {
            DrawerUtil.drawLink(g, link);
        }

        // Draw connecting line
        if (UMLManager.getInstance().getMode() instanceof LinkMode) {
            LinkMode linkMode = (LinkMode) UMLManager.getInstance().getMode();
            Point source = linkMode.getSourcePoint();
            Point target = linkMode.getTargetPoint();

            if (source == null || target == null) {
                return;
            }

            System.out.println("Draw connecting line");

            g.setColor(Color.BLACK);
            DrawerUtil.drawSingleControlPoint(g, source.x, source.y);
            g.drawLine(source.x, source.y, target.x, target.y);
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
