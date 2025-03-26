package drawers;

import java.awt.Graphics;

import core.UMLManager;
import forms.Canvas;

public class LinkDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        umlManager.getLinks().forEach(link -> link.draw(g));
    }
}
