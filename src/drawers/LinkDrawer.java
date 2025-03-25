package drawers;

import java.awt.Graphics;

import core.UMLManager;
import forms.Canvas;
import utils.DrawerUtil;

public class LinkDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        umlManager.getLinks().forEach(link -> DrawerUtil.drawLink(g, link));
    }
}
