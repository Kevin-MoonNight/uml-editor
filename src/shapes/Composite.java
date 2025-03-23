package shapes;

public class Composite {
    private BaseShape[] shapes;

    public Composite(BaseShape... shapes) {
        this.shapes = shapes;
    }

    public BaseShape[] getShapes() {
        return shapes;
    }

    public void setShapes(BaseShape... shapes) {
        this.shapes = shapes;
    }
}
