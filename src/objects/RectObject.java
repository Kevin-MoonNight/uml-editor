package objects;

import java.awt.Graphics;

import utils.DrawerUtil;

public class RectObject extends BaseObject {
    public RectObject(Boundary boundary) {
        super(boundary);
    }

    @Override
    public void draw(Graphics g, boolean isSelected) {
        DrawerUtil.drawRect(g, getBoundary(), isSelected);
        DrawerUtil.drawLabel(g, this);
    }
}
