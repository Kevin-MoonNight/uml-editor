package links;

import java.awt.*;
import java.awt.Point;

import objects.BaseObject;

public class AssociationLink extends BaseLink {
    public AssociationLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
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
        g2d.drawLine(source.x, source.y, target.x, target.y);

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

        // Draw arrow
        g2d.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        g2d.drawLine(xPoints[0], yPoints[0], xPoints[2], yPoints[2]);
    }
}
