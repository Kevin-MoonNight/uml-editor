package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import utils.BoundaryUtil;
import utils.ControlPointUtil;
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

    @Override
    public void draw(Graphics g) {
        objects.forEach(obj -> obj.draw(g));

        if (!umlManager.isSelected(this)) {
            return;
        }

        drawCompositeBox(g, getBoundary());

        ControlPointUtil.getCornerControlPoints(getBoundary())
                .forEach(p -> DrawerUtil.drawControlPoint(g, p));
    }

    private void drawCompositeBox(Graphics g, Boundary b) {
        g.setColor(Color.BLACK);
        g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    }
}
