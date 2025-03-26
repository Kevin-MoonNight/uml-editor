package drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import core.UMLManager;
import forms.Canvas;
import modes.LinkMode;
import utils.DrawerUtil;

public class LinkingDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        var linkMode = (LinkMode) umlManager.getMode();
        var source = linkMode.getSourcePoint();

        if (source == null) {
            return;
        }

        DrawerUtil.drawControlPoint(g, source);

        var target = retrieveTargetPoint(canvas, linkMode);

        drawLinkLine(g, source, target);
    }

    private Point retrieveTargetPoint(Canvas canvas, LinkMode linkMode) {
        return linkMode.getTargetPoint() != null ? linkMode.getTargetPoint() : canvas.getMousePosition();
    }

    private void drawLinkLine(Graphics g, Point source, Point target) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(Color.BLACK);

        g2d.drawLine(source.x, source.y, target.x, target.y);
    }
}
