package modes;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.UMLManager;
import events.DuringSelectEvent;
import events.StartSelectEvent;
import events.StopSelectEvent;
import objects.Boundary;
import utils.BoundaryUtil;

public class SelectMode implements Mode {
    private UMLManager umlManager;

    private Point source;
    private Point target;

    private boolean isDragging = false;
    private Point lastMousePosition;

    public SelectMode(UMLManager umlManager) {
        this.umlManager = umlManager;
    }

    public MouseAdapter getTrigger() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StartSelectEvent.handle(e, SelectMode.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                StopSelectEvent.handle(e, SelectMode.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                DuringSelectEvent.handle(e, SelectMode.this);
            }
        };
    }

    public void handle() {
        if (!isValidSelection()) {
            return;
        }

        if (isSamePosition()) {
            singleSelect(source);
        } else {
            multiSelect();
        }

        reset();
    }

    private boolean isValidSelection() {
        return source != null && target != null;
    }

    private boolean isSamePosition() {
        return source.getX() == target.getX() && source.getY() == target.getY();
    }

    private void singleSelect(Point point) {
        var targetObject = BoundaryUtil.getObjectAtPoint(umlManager.getObjects(), point);

        if (targetObject == null) {
            umlManager.clearSelectedObjects();
            return;
        }

        umlManager.updateSelectedObjects(targetObject);
    }

    private void multiSelect() {
        var boundary = new Boundary(source.x, source.y, target.x - source.x, target.y - source.y);
        var targetObjects = BoundaryUtil.getObjectsInBoundary(umlManager.getObjects(), boundary);

        if (targetObjects.size() == 0) {
            umlManager.clearSelectedObjects();
            return;
        }

        umlManager.setSelectedObjects(targetObjects);
    }

    public void reset() {
        setSource(null);
        setTarget(null);
        setIsDragging(false);
        setLastMousePosition(null);
    }

    public void setLastMousePosition(Point lastMousePosition) {
        this.lastMousePosition = lastMousePosition;
    }

    public Point getLastMousePosition() {
        return lastMousePosition;
    }

    public void setIsDragging(boolean isDragging) {
        this.isDragging = isDragging;
    }

    public boolean getIsDragging() {
        return isDragging;
    }

    public void setSource(Point source) {
        this.source = source;
    }

    public Point getSource() {
        return this.source;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public Point getTarget() {
        return target;
    }
}
