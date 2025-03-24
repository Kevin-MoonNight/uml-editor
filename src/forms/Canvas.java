package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import modes.CreateMode;
import modes.SelectMode;
import objects.Boundary;
import objects.OvalObject;
import objects.RectObject;
import utils.DrawerUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    public Canvas() {
        setup();
    }

    private void setup() {
        setLayout(null);
        setBackground(Color.lightGray);

        CanvasManager.getInstance().setCanvas(this);

        // 添加滑鼠監聽器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked at: (" + e.getX() + ", " + e.getY() + ")");

                if (e.getButton() != MouseEvent.BUTTON1) {
                    return;
                }

                if (UMLManager.getInstance().getMode() instanceof CreateMode) {
                    var createMode = (CreateMode) UMLManager.getInstance().getMode();
                    createMode.setBoundary(new Boundary(e.getX(), e.getY(), 100, 100));
                    createMode.handle();
                    return;
                }

                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setOrigin(e.getPoint());
                    return;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setOrigin(e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setDestination(e.getPoint());
                    selectMode.handle();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (UMLManager.getInstance().getMode() instanceof SelectMode) {
                    var selectMode = (SelectMode) UMLManager.getInstance().getMode();
                    selectMode.setDestination(e.getPoint());
                    update();
                }
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
