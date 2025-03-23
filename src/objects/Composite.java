package objects;

import utils.BoundaryUtil;

public class Composite extends BaseObject {
    private BaseObject[] objects;

    public Composite(BaseObject[] objects) {
        super(BoundaryUtil.getBoundaryOfObjects(objects));
        this.objects = objects;
    }

    public BaseObject[] getObjects() {
        return objects;
    }

    public void setObjects(BaseObject[] objects) {
        this.objects = objects;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }
}
