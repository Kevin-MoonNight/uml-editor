package drawers;

import java.awt.Graphics;

import core.UMLManager;

public class ObjectDrawer implements Drawable {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g) {
        umlManager.getObjects().forEach(obj -> obj.draw(g));
    }
}
