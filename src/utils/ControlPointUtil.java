package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import objects.BaseObject;
import objects.Boundary;
import objects.CompositeObject;
import objects.RectObject;

public class ControlPointUtil {
    public static Point findNearestControlPoint(BaseObject object, Point current) {
        var controlPoints = getControlPoints(object);

        if (controlPoints.size() == 0) {
            return current;
        }

        Point nearest = controlPoints.get(0);
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
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public static List<Point> getControlPoints(BaseObject obj) {
        if (obj instanceof CompositeObject) {
            return new ArrayList<>();
        }

        var middleControlPoints = getMiddleControlPoints(obj.getBoundary());

        if (obj instanceof RectObject) {
            var cornerControlPoints = getCornerControlPoints(obj.getBoundary());

            List<Point> points = new ArrayList<>();
            points.addAll(middleControlPoints);
            points.addAll(cornerControlPoints);

            return points;
        }

        return middleControlPoints;
    }

    public static List<Point> getAllControlPoints(Boundary b) {
        var middleControlPoints = getMiddleControlPoints(b);
        var cornerControlPoints = getCornerControlPoints(b);

        List<Point> points = new ArrayList<>();
        points.addAll(middleControlPoints);
        points.addAll(cornerControlPoints);

        return points;
    }

    public static List<Point> getMiddleControlPoints(Boundary b) {
        return List.of(
                new Point(b.getX() + b.getWidth() / 2, b.getY()), // 上
                new Point(b.getX() + b.getWidth(), b.getY() + b.getHeight() / 2), // 右
                new Point(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight()), // 下
                new Point(b.getX(), b.getY() + b.getHeight() / 2) // 左
        );
    }

    public static List<Point> getCornerControlPoints(Boundary b) {
        return List.of(
                new Point(b.getX(), b.getY()), // 左上
                new Point(b.getX(), b.getY() + b.getHeight()), // 左下
                new Point(b.getX() + b.getWidth(), b.getY()), // 右上
                new Point(b.getX() + b.getWidth(), b.getY() + b.getHeight()) // 右下
        );
    }
}
