package drawers;

import java.awt.Graphics;

import core.UMLManager;
import forms.Canvas;
import modes.SelectMode;
import objects.Boundary;
import utils.DrawerUtil;

public class SelectBoxDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        var selectMode = (SelectMode) umlManager.getMode();
        var origin = selectMode.getSource();
        var destination = selectMode.getTarget();

        if (origin != null && destination != null) {
            var boundary = new Boundary(
                    Math.min(origin.x, destination.x),
                    Math.min(origin.y, destination.y),
                    Math.abs(destination.x - origin.x),
                    Math.abs(destination.y - origin.y));
            DrawerUtil.drawSelectBox(g, boundary);
        }
    }
}
