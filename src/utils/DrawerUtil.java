package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import objects.Boundary;

public class DrawerUtil {

    public static void drawRect(Graphics g, Boundary boundary) {
        drawRect(g, boundary, false);
    }

    public static void drawRect(Graphics g, Boundary boundary, boolean isSelected) {
        int x = boundary.getX();
        int y = boundary.getY();
        int width = boundary.getWidth();
        int height = boundary.getHeight();
        System.out
                .println("Drawing a rectangle at (" + x + ", " + y + ") with width " + width + " and height " + height);

        if (isSelected) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawRect(x, y, width, height);
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
    }

    public static void drawOval(Graphics g, Boundary boundary) {
        drawOval(g, boundary, false);
    }

    public static void drawOval(Graphics g, Boundary boundary, boolean isSelected) {
        int x = boundary.getX();
        int y = boundary.getY();
        int width = boundary.getWidth();
        int height = boundary.getHeight();
        System.out.println("Drawing an oval at (" + x + ", " + y + ") with width " + width + " and height " + height);

        if (isSelected) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawOval(x, y, width, height);
        g.setColor(Color.GRAY);
        g.fillOval(x, y, width, height);
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
}
