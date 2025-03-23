package shapes;

public class BaseShape {
    protected ShapeLabel label;
    protected Boundary boundary;

    public BaseShape(Boundary boundary) {
        this.boundary = boundary;
    }

    public ShapeLabel getLabel() {
        return label;
    }

    public void setLabel(ShapeLabel label) {
        this.label = label;
    }
}
