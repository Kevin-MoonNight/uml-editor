package objects;

import java.util.ArrayList;
import java.util.List;

import utils.BoundaryUtil;

public class CompositeObject extends BaseObject {
    private List<BaseObject> objects = new ArrayList<>();

    public CompositeObject(List<BaseObject> objects) {
        super(BoundaryUtil.getBoundaryOfObjects(objects));
        this.objects.addAll(objects);
    }

    public List<BaseObject> getObjects() {
        return objects;
    }

    public void setObjects(List<BaseObject> objects) {
        this.objects.clear();
        this.objects.addAll(objects);
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }
}
