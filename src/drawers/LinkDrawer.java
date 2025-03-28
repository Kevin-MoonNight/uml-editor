package drawers;

import java.awt.Graphics;
import java.util.Objects;

import core.UMLManager;

public class LinkDrawer implements Drawable {
    private final UMLManager umlManager;

    public LinkDrawer(UMLManager umlManager) {
        Objects.requireNonNull(umlManager, "UMLManager cannot be null");

        this.umlManager = umlManager;
    }

    @Override
    public void draw(Graphics g) {
        umlManager.getLinks().forEach(link -> link.draw(g));
    }
}
