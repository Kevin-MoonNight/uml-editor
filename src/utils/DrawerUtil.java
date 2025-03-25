package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import forms.Canvas;

import java.awt.Font;
import java.awt.FontMetrics;

import links.BaseLink;
import objects.Boundary;
import objects.BaseObject;
import objects.CompositeObject;
import objects.ObjectLabel;
import objects.RectObject;
import objects.OvalObject;

public class DrawerUtil {

    public static void drawRect(Graphics g, Boundary boundary) {
        drawRect(g, boundary, false);
    }

    public static void drawRect(Graphics g, Boundary boundary, boolean isSelected) {
        System.out.println("Drawing a rectangle at (" + boundary.getX() + ", " + boundary.getY() +
                ") with width " + boundary.getWidth() + " and height " + boundary.getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
        g.setColor(Color.GRAY);
        g.fillRect(boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());

        if (isSelected) {
            drawControlPoints(g, boundary, true);
        }
    }

    public static void drawOval(Graphics g, Boundary boundary) {
        drawOval(g, boundary, false);
    }

    public static void drawOval(Graphics g, Boundary boundary, boolean isSelected) {
        System.out.println("Drawing an oval at (" + boundary.getX() + ", " + boundary.getY() +
                ") with width " + boundary.getWidth() + " and height " + boundary.getHeight());

        g.setColor(Color.BLACK);
        g.drawOval(boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());
        g.setColor(Color.GRAY);
        g.fillOval(boundary.getX(), boundary.getY(), boundary.getWidth(), boundary.getHeight());

        if (isSelected) {
            drawControlPoints(g, boundary, false);
        }
    }

    private static void drawControlPoints(Graphics g, Boundary boundary, boolean includeCorners) {
        int x = boundary.getX();
        int y = boundary.getY();
        int width = boundary.getWidth();
        int height = boundary.getHeight();

        g.setColor(Color.BLACK);

        // Draw corner control points if needed
        if (includeCorners) {
            drawSingleControlPoint(g, x, y); // Top-left
            drawSingleControlPoint(g, x + width, y); // Top-right
            drawSingleControlPoint(g, x, y + height); // Bottom-left
            drawSingleControlPoint(g, x + width, y + height); // Bottom-right
        }

        // Draw middle control points
        drawSingleControlPoint(g, x + (width / 2), y); // Top
        drawSingleControlPoint(g, x + width, y + (height / 2)); // Right
        drawSingleControlPoint(g, x + (width / 2), y + height); // Bottom
        drawSingleControlPoint(g, x, y + (height / 2)); // Left
    }

    public static void drawSingleControlPoint(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        int controlSize = 10;
        int halfControl = controlSize / 2;
        g.fillRect(x - halfControl, y - halfControl, controlSize, controlSize);
    }

    public static void drawSelectBox(Graphics g, Boundary boundary) {
        int x = boundary.getX();
        int y = boundary.getY();
        int width = boundary.getWidth();
        int height = boundary.getHeight();
        System.out.println(
                "Drawing a select box at (" + x + ", " + y + ") with width " + width + " and height " + height);

        Graphics2D g2d = (Graphics2D) g;
        // 保存原始設置
        java.awt.Composite originalComposite = g2d.getComposite();

        // 設置半透明度 (0.3f 表示 30% 的不透明度)
        g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.3f));
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, width, height);

        // 恢復原始設置
        g2d.setComposite(originalComposite);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }

    public static void drawCompositeObject(Graphics g, CompositeObject compositeObj, boolean isSelected) {
        // Draw all contained objects
        for (BaseObject obj : compositeObj.getObjects()) {
            if (obj instanceof RectObject) {
                drawRect(g, obj.getBoundary(), false);
            } else if (obj instanceof OvalObject) {
                drawOval(g, obj.getBoundary(), false);
            } else if (obj instanceof CompositeObject) {
                drawCompositeObject(g, (CompositeObject) obj, false);
            }
        }

        Boundary boundary = compositeObj.getBoundary();
        // Draw selection border if selected
        if (isSelected) {
            g.setColor(Color.BLACK);
            g.drawRect(boundary.getX(), boundary.getY(),
                    boundary.getWidth(), boundary.getHeight());

            // Draw control points
            int controlSize = 10;
            int halfControl = controlSize / 2;
            g.setColor(Color.BLACK);

            // Draw corner control points
            g.fillRect(boundary.getX() - halfControl,
                    boundary.getY() - halfControl,
                    controlSize, controlSize); // Top-left
            g.fillRect(boundary.getX() + boundary.getWidth() - halfControl,
                    boundary.getY() - halfControl,
                    controlSize, controlSize); // Top-right
            g.fillRect(boundary.getX() - halfControl,
                    boundary.getY() + boundary.getHeight() - halfControl,
                    controlSize, controlSize); // Bottom-left
            g.fillRect(boundary.getX() + boundary.getWidth() - halfControl,
                    boundary.getY() + boundary.getHeight() - halfControl,
                    controlSize, controlSize); // Bottom-right
        }
    }

    public static void drawLink(Graphics g, BaseLink link) {
        link.drawLink(g);

        // Draw control points
        Point source = link.getSourcePoint();
        Point target = link.getTargetPoint();
        drawSingleControlPoint(g, source.x, source.y);
        drawSingleControlPoint(g, target.x, target.y);
    }

    public static void drawLinkLine(Graphics g, Point source, Point target) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(Color.BLACK);

        g2d.drawLine(source.x, source.y, target.x, target.y);
    }

    public static void drawLabel(Graphics g, BaseObject object) {
        ObjectLabel label = object.getLabel();
        if (label == null)
            return;

        Boundary objBoundary = object.getBoundary();

        // Calculate label size (80% of object size)
        int labelWidth = (int) (objBoundary.getWidth() * 0.8);
        int labelHeight = (int) (objBoundary.getHeight() * 0.8);

        // Calculate label position (centered)
        int labelX = objBoundary.getX() + (objBoundary.getWidth() - labelWidth) / 2;
        int labelY = objBoundary.getY() + (objBoundary.getHeight() - labelHeight) / 2;

        // Draw label shape
        g.setColor(label.getColor());
        if ("Rectangle".equals(label.getShape())) {
            g.fillRect(labelX, labelY, labelWidth, labelHeight);
            g.setColor(Color.BLACK);
            g.drawRect(labelX, labelY, labelWidth, labelHeight);
        } else { // Oval
            g.fillOval(labelX, labelY, labelWidth, labelHeight);
            g.setColor(Color.BLACK);
            g.drawOval(labelX, labelY, labelWidth, labelHeight);
        }

        // Draw text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, label.getFontSize()));
        FontMetrics metrics = g.getFontMetrics();
        String text = label.getName();

        // Calculate text position (centered in label)
        int textX = labelX + (labelWidth - metrics.stringWidth(text)) / 2;
        int textY = labelY + (labelHeight - metrics.getHeight()) / 2 + metrics.getAscent();

        g.drawString(text, textX, textY);
    }

    public static void clear(Canvas canvas, Graphics g) {
        g.setColor(Color.lightGray);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
