package links;

import java.awt.Point;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import objects.BaseObject;
import utils.DrawerUtil;

public class GeneralizationLink extends BaseLink {
    public GeneralizationLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        super(source, target, sourcePoint, targetPoint);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));

        Point source = getSourcePoint();
        Point target = getTargetPoint();

        // Draw main line
        g2d.setColor(Color.BLACK);

        // Calculate arrow properties
        double angle = Math.atan2(target.y - source.y, target.x - source.x);
        int arrowLength = 20;

        // Calculate arrow points
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = target.x;
        yPoints[0] = target.y;
        xPoints[1] = target.x - (int) (arrowLength * Math.cos(angle - Math.PI / 6));
        yPoints[1] = target.y - (int) (arrowLength * Math.sin(angle - Math.PI / 6));
        xPoints[2] = target.x - (int) (arrowLength * Math.cos(angle + Math.PI / 6));
        yPoints[2] = target.y - (int) (arrowLength * Math.sin(angle + Math.PI / 6));

        // Draw line to triangle base
        g2d.drawLine(source.x, source.y,
                target.x - (int) (arrowLength * Math.cos(angle)),
                target.y - (int) (arrowLength * Math.sin(angle)));

        // Draw triangle
        g2d.drawPolygon(xPoints, yPoints, 3);

        DrawerUtil.drawControlPoint(g, getSourcePoint());
        DrawerUtil.drawControlPoint(g, getTargetPoint());
    }
}
