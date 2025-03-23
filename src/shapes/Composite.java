package shapes;

import utils.BoundaryUtil;

public class Composite extends BaseShape {
    private BaseShape[] shapes;

    public Composite(BaseShape[] shapes) {
        super(BoundaryUtil.getBoundaryOfShapes(shapes));
        this.shapes = shapes;
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
