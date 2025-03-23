package shapes;

public class Composite {
    private BaseShape[] shapes;
    private Boundary boundary;

    public Composite(BaseShape[] shapes, Boundary boundary) {
        this.shapes = shapes;
        this.boundary = boundary;
    }

    public BaseShape[] getShapes() {
        return shapes;
    }

    public void setShapes(BaseShape[] shapes) {
        this.shapes = shapes;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }
}
