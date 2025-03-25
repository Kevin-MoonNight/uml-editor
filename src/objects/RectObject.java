package objects;

import java.awt.Graphics;

import utils.DrawerUtil;
import utils.LineUtil;

public class RectObject extends BaseObject {
    public RectObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g, boolean isSelected) {
        DrawerUtil.drawRect(g, getBoundary());
        DrawerUtil.drawLabel(g, this);

        if (!isSelected) {
            return;
        }

        LineUtil.getAllControlPoints(getBoundary())
                .forEach(p -> DrawerUtil.drawSingleControlPoint(g, p.x, p.y));
    }
}
