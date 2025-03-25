package links;

import java.awt.Point;

import objects.BaseObject;
import utils.LineUtil;

public class BaseLink {
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
        sourcePoint = LineUtil.findNearestControlPoint(source, sourcePoint);
        targetPoint = LineUtil.findNearestControlPoint(target, targetPoint);
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
}
