package drawers;

import java.awt.Graphics;

import core.UMLManager;
import forms.Canvas;

public class SelectedObjectDrawer implements Drawer {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g, Canvas canvas) {
        umlManager.getSelectedObjects().forEach(
                object -> object.draw(g, true));
    }
}
