package objects;

public class BaseObject implements Boundable {
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
}
