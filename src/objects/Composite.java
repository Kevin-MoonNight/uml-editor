package objects;

import java.util.List;

import utils.BoundaryUtil;

public class Composite extends BaseObject {
    private List<BaseObject> objects;

    public Composite(List<BaseObject> objects) {
        super(BoundaryUtil.getBoundaryOfObjects(objects));
        this.objects = objects;
    }

    public List<BaseObject> getObjects() {
        return objects;
    }

    public void setObjects(List<BaseObject> objects) {
        this.objects = objects;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }
}
