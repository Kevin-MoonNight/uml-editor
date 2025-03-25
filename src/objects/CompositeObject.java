package objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import utils.BoundaryUtil;
import utils.DrawerUtil;
import utils.LineUtil;

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

    @Override
    public void draw(Graphics g, boolean isSelected) {
        getObjects().forEach(obj -> obj.draw(g, false));

        if (!isSelected) {
            return;
        }

        DrawerUtil.drawCompositeBox(g, getBoundary());
        LineUtil.getCornerControlPoints(getBoundary())
                .forEach(p -> DrawerUtil.drawSingleControlPoint(g, p.x, p.y));
    }
}
