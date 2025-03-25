package drawers;

import java.awt.Graphics;

import core.UMLManager;
import forms.Canvas;

public class UnSelectedObjectDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        umlManager.getUnSelectedObjects().forEach(
                object -> object.draw(g, false));
    }
}
