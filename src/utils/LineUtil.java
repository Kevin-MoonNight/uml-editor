package utils;

import java.awt.Point;

import objects.BaseObject;
import objects.Boundary;
import objects.CompositeObject;
import objects.RectObject;

public class LineUtil {
    public static Point findNearestControlPoint(BaseObject object, Point current) {
        Point[] controlPoints = getControlPoints(object);

        if (controlPoints.length == 0) {
            return current;
        }

        Point nearest = controlPoints[0];
        double minDistance = distance(nearest, current);

        for (Point p : controlPoints) {
            double d = distance(p, current);
            if (d < minDistance) {
                minDistance = d;
                nearest = p;
            }
        }
        return nearest;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(
                Math.pow(p2.x - p1.x, 2) +
                        Math.pow(p2.y - p1.y, 2));
    }

    private static Point[] getControlPoints(BaseObject obj) {
        Boundary b = obj.getBoundary();

        if (obj instanceof CompositeObject) {
            return new Point[] {};
        }

        Point[] middleControlPoints = getMiddleControlPoints(obj, b);

        if (obj instanceof RectObject) {
            Point[] cornerControlPoints = getCornerControlPoints(obj, b);
            Point[] result = new Point[middleControlPoints.length + cornerControlPoints.length];
            System.arraycopy(middleControlPoints, 0, result, 0, middleControlPoints.length);
            System.arraycopy(cornerControlPoints, 0, result, middleControlPoints.length, cornerControlPoints.length);
            return result;
        }

        return middleControlPoints;
    }

    private static Point[] getMiddleControlPoints(BaseObject obj, Boundary b) {
        return new Point[] {
                new Point(b.getX() + b.getWidth() / 2, b.getY()), // 上
                new Point(b.getX() + b.getWidth(), b.getY() + b.getHeight() / 2), // 右
                new Point(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight()), // 下
                new Point(b.getX(), b.getY() + b.getHeight() / 2) // 左
        };
    }

    private static Point[] getCornerControlPoints(BaseObject obj, Boundary b) {
        return new Point[] {
                new Point(b.getX(), b.getY()), // 左上
                new Point(b.getX(), b.getY() + b.getHeight()), // 左下
                new Point(b.getX() + b.getWidth(), b.getY()), // 右上
                new Point(b.getX() + b.getWidth(), b.getY() + b.getHeight()) // 右下
        };
    }
}
