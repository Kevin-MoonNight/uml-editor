package drawers;

import java.awt.Graphics;
import java.util.Objects;

import core.UMLManager;

public class ObjectDrawer implements Drawable {
    private final UMLManager umlManager;

    public ObjectDrawer(UMLManager umlManager) {
        Objects.requireNonNull(umlManager, "UMLManager cannot be null");

        this.umlManager = umlManager;
    }

    @Override
    public void draw(Graphics g) {
        umlManager.getObjects().forEach(obj -> obj.draw(g));
    }
}
