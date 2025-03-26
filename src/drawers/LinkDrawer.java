package drawers;

import java.awt.Graphics;

import core.UMLManager;

public class LinkDrawer implements Drawable {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g) {
        umlManager.getLinks().forEach(link -> link.draw(g));
    }
}
