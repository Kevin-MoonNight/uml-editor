package objects;

import java.awt.Graphics;

import core.UMLManager;

public class BaseObject implements Boundable, Drawable {
    protected UMLManager umlManager = UMLManager.getInstance();

    protected ObjectLabel label;
    protected Boundary boundary;

    public BaseObject(Boundary boundary) {
        this.boundary = boundary;
    }

    public ObjectLabel getLabel() {
        return label;
    }

    public void setLabel(ObjectLabel label) {
        this.label = label;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
