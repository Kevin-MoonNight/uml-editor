package links;

import java.awt.Point;
import java.awt.Graphics;

import objects.BaseObject;
import objects.Drawable;
import utils.ControlPointUtil;

public class BaseLink implements Drawable {
    private BaseObject source;
    private BaseObject target;
    private Point sourcePoint;
    private Point targetPoint;

    public BaseLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        this.source = source;
        this.target = target;
        this.sourcePoint = sourcePoint;
        this.targetPoint = targetPoint;
        updateConnectionPoints();
    }

    public void updateConnectionPoints() {
        // 找到最近的控制點
        sourcePoint = ControlPointUtil.findNearestControlPoint(source, sourcePoint);
        targetPoint = ControlPointUtil.findNearestControlPoint(target, targetPoint);
    }

    public BaseObject getSource() {
        return source;
    }

    public BaseObject getTarget() {
        return target;
    }

    public void setSource(BaseObject origin) {
        this.source = origin;
    }

    public void setTarget(BaseObject destination) {
        this.target = destination;
    }

    public Point getSourcePoint() {
        return sourcePoint;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
