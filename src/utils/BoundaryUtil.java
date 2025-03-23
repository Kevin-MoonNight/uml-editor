package utils;

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
    public static Boundary getBoundaryOfObjects(BaseObject[] objects) {
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
}
