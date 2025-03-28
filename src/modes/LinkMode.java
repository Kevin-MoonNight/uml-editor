package modes;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.UMLManager;
import events.DuringLinkEvent;
import events.StartLinkEvent;
import events.StopLinkEvent;
import links.BaseLink;
import objects.BaseObject;
import objects.CompositeObject;
import utils.ControlPointUtil;

public abstract class LinkMode implements Mode {
    protected UMLManager umlManager;

    private BaseObject source;
    private BaseObject target;

    private Point sourcePoint;
    private Point targetPoint;

    public LinkMode(UMLManager umlManager) {
        this.umlManager = umlManager;
    }

    public MouseAdapter getTrigger() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StartLinkEvent.handle(e, LinkMode.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                StopLinkEvent.handle(e, LinkMode.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                DuringLinkEvent.handle(e, LinkMode.this);
            }
        };
    }

    public void handle() {
        if (isLinkValid(source, target)) {
            BaseLink link = createLink(source, target, sourcePoint, targetPoint);

            umlManager.addLink(link);
        }

        reset();
    }

    private boolean isLinkValid(BaseObject source, BaseObject target) {
        return isValidObject(source) && isValidObject(target) && !source.equals(target);
    }

    private boolean isValidObject(BaseObject object) {
        return object != null && !(object instanceof CompositeObject);
    }

    protected abstract BaseLink createLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint);

    public void reset() {
        source = null;
        target = null;
        sourcePoint = null;
        targetPoint = null;
    }

    public void setSource(BaseObject source) {
        if (!isValidObject(source)) {
            return;
        }

        this.source = source;
    }

    public void setTarget(BaseObject target) {
        if (!isValidObject(target)) {
            return;
        }

        this.target = target;
    }

    public BaseObject getSource() {
        return source;
    }

    public BaseObject getTarget() {
        return target;
    }

    public void setSourcePoint(Point sourcePoint) {
        this.sourcePoint = sourcePoint;

        if (this.source != null) {
            this.sourcePoint = ControlPointUtil.findNearestControlPoint(source, sourcePoint);
        }
    }

    public void setTargetPoint(Point targetPoint) {
        this.targetPoint = targetPoint;

        if (this.target != null) {
            this.targetPoint = ControlPointUtil.findNearestControlPoint(target, targetPoint);
        }
    }

    public Point getSourcePoint() {
        return sourcePoint;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }
}
