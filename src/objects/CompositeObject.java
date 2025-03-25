package objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import utils.BoundaryUtil;
import utils.DrawerUtil;

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

    @Override
    public void draw(Graphics g, boolean isSelected) {
        DrawerUtil.drawCompositeObject(g, this, isSelected);
    }
}
