package objects;

import java.awt.Color;
import java.awt.Graphics;

import utils.ControlPointUtil;
import utils.DrawerUtil;

public class OvalObject extends BaseObject {
    public OvalObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g) {
        Boundary b = getBoundary();
        g.setColor(Color.BLACK);
        g.drawOval(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        g.setColor(Color.GRAY);
        g.fillOval(b.getX(), b.getY(), b.getWidth(), b.getHeight());

        if (!umlManager.isSelected(this)) {
            return;
        }

        ControlPointUtil.getMiddleControlPoints(b)
                .forEach(p -> DrawerUtil.drawControlPoint(g, p));

    }
}
