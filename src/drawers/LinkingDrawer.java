package drawers;

import java.awt.Graphics;
import java.awt.Point;

import core.UMLManager;
import forms.Canvas;
import modes.LinkMode;
import utils.DrawerUtil;

public class LinkingDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        var linkMode = (LinkMode) umlManager.getMode();
        var source = linkMode.getSourcePoint();
        
        if (source == null) {
            return;
        }

        DrawerUtil.drawSingleControlPoint(g, source.x, source.y);

        var target = retrieveTargetPoint(canvas, linkMode);

        DrawerUtil.drawLinkLine(g, source, target);
    }

    private Point retrieveTargetPoint(Canvas canvas, LinkMode linkMode) {
        return linkMode.getTargetPoint() != null ? linkMode.getTargetPoint() : canvas.getMousePosition();
    }
}
