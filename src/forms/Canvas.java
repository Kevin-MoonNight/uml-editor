package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import modes.LinkMode;
import modes.SelectMode;
import objects.Boundary;
import objects.CompositeObject;
import objects.OvalObject;
import objects.RectObject;
import utils.DrawerUtil;

import java.awt.*;

public class Canvas extends JPanel {
    public Canvas() {
        setup();
    }

    public void setup() {
        setLayout(null);
        setBackground(Color.lightGray);

        CanvasManager.getInstance().setCanvas(this);
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
                DrawerUtil.drawLabel(g, object);
            } else if (object instanceof OvalObject) {
                DrawerUtil.drawOval(g, object.getBoundary(), isSelected);
                DrawerUtil.drawLabel(g, object);
            } else if (object instanceof CompositeObject) {
                DrawerUtil.drawCompositeObject(g, (CompositeObject) object, isSelected);
            }
        }

        // Draw the select box
        if (UMLManager.getInstance().getMode() instanceof SelectMode) {
            var selectMode = (SelectMode) UMLManager.getInstance().getMode();
            var origin = selectMode.getSource();
            var destination = selectMode.getTarget();

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
            System.out.println("Draw connecting line");
            LinkMode linkMode = (LinkMode) UMLManager.getInstance().getMode();
            Point source = linkMode.getSourcePoint();
            Point target = linkMode.getTargetPoint();

            if (source == null) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.setColor(Color.BLACK);
            DrawerUtil.drawSingleControlPoint(g, source.x, source.y);

            if (target == null) {
                var currentMousePosition = getMousePosition();
                g2d.drawLine(source.x, source.y, currentMousePosition.x, currentMousePosition.y);
                return;
            }

            g2d.drawLine(source.x, source.y, target.x, target.y);
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
