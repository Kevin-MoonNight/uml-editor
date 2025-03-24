package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import objects.BaseObject;
import objects.Boundary;

public class BoundaryUtil {
    /**
     * Calculates the bounding box that encompasses all the given objects.
     * 
     * @param objects An array of BaseObject objects to calculate the boundary for
     * @return A Boundary object representing the smallest rectangle that contains
     *         all the input objects
     */
    public static Boundary getBoundaryOfObjects(List<BaseObject> objects) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (BaseObject object : objects) {
            Boundary objectBoundary = object.getBoundary();
            minX = Math.min(minX, objectBoundary.getX());
            minY = Math.min(minY, objectBoundary.getY());
            maxX = Math.max(maxX, objectBoundary.getX() + objectBoundary.getWidth());
            maxY = Math.max(maxY, objectBoundary.getY() + objectBoundary.getHeight());
        }

        return new Boundary(
                minX,
                minY,
                maxX - minX,
                maxY - minY);
    }

    public static List<BaseObject> getObjectsInBoundary(List<BaseObject> objects, Boundary boundary) {
        int count = 0;
        for (BaseObject object : objects) {
            if (isObjectInBoundary(object, boundary)) {
                count++;
            }
        }

        List<BaseObject> objectsInBoundary = new ArrayList<>(count);
        for (BaseObject object : objects) {
            if (isObjectInBoundary(object, boundary)) {
                objectsInBoundary.add(object);
            }
        }

        return objectsInBoundary;
    }

    private static boolean isObjectInBoundary(BaseObject object, Boundary boundary) {
        Boundary objectBoundary = object.getBoundary();

        // Normalize the selection boundary coordinates
        int selectionX1 = boundary.getX();
        int selectionY1 = boundary.getY();
        int selectionX2 = boundary.getX() + boundary.getWidth();
        int selectionY2 = boundary.getY() + boundary.getHeight();

        // Swap coordinates if width/height is negative
        if (selectionX2 < selectionX1) {
            int temp = selectionX1;
            selectionX1 = selectionX2;
            selectionX2 = temp;
        }
        if (selectionY2 < selectionY1) {
            int temp = selectionY1;
            selectionY1 = selectionY2;
            selectionY2 = temp;
        }

        // Get object boundary coordinates
        int objectX1 = objectBoundary.getX();
        int objectY1 = objectBoundary.getY();
        int objectX2 = objectBoundary.getX() + objectBoundary.getWidth();
        int objectY2 = objectBoundary.getY() + objectBoundary.getHeight();

        return objectX1 >= selectionX1
                && objectY1 >= selectionY1
                && objectX2 <= selectionX2
                && objectY2 <= selectionY2;
    }

    public static BaseObject getObjectsAtPoint(List<BaseObject> objects, Point point) {
        for (BaseObject object : objects) {
            if (isObjectAtPoint(object, point)) {
                return object;
            }
        }

        return null;
    }

    private static boolean isObjectAtPoint(BaseObject object, Point point) {
        Boundary objectBoundary = object.getBoundary();
        return point.x >= objectBoundary.getX()
                && point.y >= objectBoundary.getY()
                && point.x <= objectBoundary.getX() + objectBoundary.getWidth()
                && point.y <= objectBoundary.getY() + objectBoundary.getHeight();
    }
}
