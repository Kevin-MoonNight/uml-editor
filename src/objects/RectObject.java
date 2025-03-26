package objects;

import java.awt.Color;
import java.awt.Graphics;

import utils.ControlPointUtil;
import utils.DrawerUtil;

public class RectObject extends BaseObject {
    public RectObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g) {
        Boundary b = getBoundary();
        g.setColor(Color.BLACK);
        g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        g.setColor(Color.GRAY);
        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

        if (!umlManager.isSelected(this)) {
            return;
        }

        ControlPointUtil.getAllControlPoints(b)
                .forEach(p -> DrawerUtil.drawControlPoint(g, p));
    }
}
