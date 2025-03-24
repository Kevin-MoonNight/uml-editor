package modes;

import java.awt.Point;

import core.UMLManager;
import objects.BaseObject;
import objects.Boundary;
import utils.BoundaryUtil;

public class SelectMode implements Mode {
    private Point origin;
    private Point destination;

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public Point getDestination() {
        return destination;
    }

    public void handle() {
        System.out.println("SelectMode");
        if (origin == null || destination == null) {
            return;
        }

        if (origin.getX() == destination.getX() && origin.getY() == destination.getY()) {
            singleSelect(origin);
        } else {
            multiSelect();
        }

        this.origin = null;
        this.destination = null;
    }

    private void singleSelect(Point point) {
        var objects = UMLManager.getInstance().getObjects();

        BaseObject selectObject = BoundaryUtil.getObjectsAtPoint(objects, point);

        if (selectObject == null) {
            return;
        }

        UMLManager.getInstance().getSelectedObjects().clear();
        UMLManager.getInstance().getSelectedObjects().add(selectObject);
        UMLManager.getInstance().removeObject(selectObject);
        UMLManager.getInstance().addObject(selectObject);
    }

    private void multiSelect() {
        var objects = UMLManager.getInstance().getObjects();

        var boundary = new Boundary(origin.x, origin.y, destination.x - origin.x, destination.y - origin.y);
        var selectObjects = BoundaryUtil.getObjectsInBoundary(objects, boundary);

        UMLManager.getInstance().setSelectedObjects(selectObjects);
    }
}
