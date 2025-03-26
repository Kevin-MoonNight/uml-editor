package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import forms.Canvas;

public class DrawerUtil {
    private static final int CONTROL_SIZE = 10;

    public static void drawControlPoint(Graphics g, Point p) {
        int halfControl = CONTROL_SIZE / 2;

        g.setColor(Color.BLACK);
        g.fillRect(p.x - halfControl, p.y - halfControl, CONTROL_SIZE, CONTROL_SIZE);
    }

    public static void clear(Canvas canvas, Graphics g) {
        g.setColor(Color.lightGray);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
