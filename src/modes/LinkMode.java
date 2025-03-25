package modes;

import java.awt.Point;

import core.UMLManager;
import links.AssociationLink;
import links.BaseLink;
import links.CompositionLink;
import links.GeneralizationLink;
import links.LinkType;
import objects.BaseObject;
import objects.CompositeObject;
import utils.LineUtil;

public class LinkMode implements Mode {
    private LinkType linkType;
    private BaseObject source;
    private BaseObject target;
    private Point sourcePoint;
    private Point targetPoint;

    public LinkMode(LinkType linkType) {
        this.linkType = linkType;
    }

    public void setSource(BaseObject origin) {
        if (source instanceof CompositeObject) {
            return;
        }

        this.source = origin;
    }

    public void setTarget(BaseObject destination) {
        if (target instanceof CompositeObject) {
            return;
        }

        this.target = destination;
    }

    public BaseObject getSource() {
        return source;
    }

    public BaseObject getTarget() {
        return target;
    }

    public void setSourcePoint(Point sourcePoint) {
        System.out.println("setSourcePoint");
        this.sourcePoint = sourcePoint;

        if (this.source != null) {
            this.sourcePoint = LineUtil.findNearestControlPoint(source, sourcePoint);
        }
    }

    public void setTargetPoint(Point targetPoint) {
        System.out.println("setTargetPoint");
        this.targetPoint = targetPoint;

        if (this.target != null) {
            this.targetPoint = LineUtil.findNearestControlPoint(target, targetPoint);
        }
    }

    public Point getSourcePoint() {
        return sourcePoint;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    public void handle() {
        System.out.println("LinkMode");

        if (source instanceof CompositeObject) {
            reset();
            return;
        }

        if (target instanceof CompositeObject) {
            reset();
            return;
        }

        if (source == null || target == null) {
            reset();
            return;
        }

        if (source.equals(target)) {
            reset();
            return;
        }

        BaseLink link = null;

        switch (linkType) {
            case ASSOCIATION:
                link = new AssociationLink(source, target, sourcePoint, targetPoint);
                break;
            case GENERALIZATION:
                link = new GeneralizationLink(source, target, sourcePoint, targetPoint);
                break;
            case COMPOSITION:
                link = new CompositionLink(source, target, sourcePoint, targetPoint);
                break;
            default:
                break;
        }

        UMLManager.getInstance().addLink(link);

        reset();
    }

    public void reset() {
        source = null;
        target = null;
        sourcePoint = null;
        targetPoint = null;
    }
}
