package links;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import objects.BaseObject;

public class CompositionLink extends BaseLink {
    public CompositionLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        super(source, target, sourcePoint, targetPoint);
    }

    @Override
    public void drawLink(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));

        Point source = getSourcePoint();
        Point target = getTargetPoint();

        // Draw main line
        g2d.setColor(Color.BLACK);

        // Calculate arrow properties
        double angle = Math.atan2(target.y - source.y, target.x - source.x);
        int diamondLength = 10;
        int diamondWidth = 10;

        // Calculate diamond points
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        xPoints[0] = target.x;
        yPoints[0] = target.y;
        xPoints[1] = target.x - (int) (diamondLength * Math.cos(angle) + diamondWidth * Math.sin(angle));
        yPoints[1] = target.y - (int) (diamondLength * Math.sin(angle) - diamondWidth * Math.cos(angle));
        xPoints[2] = target.x - (int) (2 * diamondLength * Math.cos(angle));
        yPoints[2] = target.y - (int) (2 * diamondLength * Math.sin(angle));
        xPoints[3] = target.x - (int) (diamondLength * Math.cos(angle) - diamondWidth * Math.sin(angle));
        yPoints[3] = target.y - (int) (diamondLength * Math.sin(angle) + diamondWidth * Math.cos(angle));

        // Draw line to diamond
        g2d.drawLine(source.x, source.y, xPoints[2], yPoints[2]);

        // Draw diamond
        g2d.drawPolygon(xPoints, yPoints, 4);
    }
}
