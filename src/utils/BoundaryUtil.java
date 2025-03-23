package utils;

import shapes.BaseShape;
import shapes.Boundary;

public class BoundaryUtil {
    /**
     * Calculates the bounding box that encompasses all the given shapes.
     * 
     * @param shapes An array of BaseShape objects to calculate the boundary for
     * @return A Boundary object representing the smallest rectangle that contains
     *         all the input shapes
     */
    public static Boundary getBoundaryOfShapes(BaseShape[] shapes) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (BaseShape shape : shapes) {
            Boundary shapeBoundary = shape.getBoundary();
            minX = Math.min(minX, shapeBoundary.getX());
            minY = Math.min(minY, shapeBoundary.getY());
            maxX = Math.max(maxX, shapeBoundary.getX() + shapeBoundary.getWidth());
            maxY = Math.max(maxY, shapeBoundary.getY() + shapeBoundary.getHeight());
        }

        return new Boundary(
                minX,
                minY,
                maxX - minX,
                maxY - minY);
    }
}
