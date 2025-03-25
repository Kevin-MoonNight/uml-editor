package objects;

import java.awt.Graphics;

import utils.DrawerUtil;
import utils.LineUtil;

public class OvalObject extends BaseObject {
    public OvalObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g, boolean isSelected) {
        DrawerUtil.drawOval(g, getBoundary());
        DrawerUtil.drawLabel(g, this);

        if (!isSelected) {
            return;
        }

        LineUtil.getMiddleControlPoints(getBoundary())
                .forEach(p -> DrawerUtil.drawSingleControlPoint(g, p.x, p.y));
    }
}
