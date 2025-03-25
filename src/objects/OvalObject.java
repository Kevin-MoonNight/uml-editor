package objects;

import java.awt.Graphics;

import utils.DrawerUtil;

public class OvalObject extends BaseObject {
    public OvalObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g, boolean isSelected) {
        DrawerUtil.drawOval(g, getBoundary(), isSelected);
        DrawerUtil.drawLabel(g, this);
    }
}
