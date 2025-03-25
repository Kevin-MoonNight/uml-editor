package links;

import java.awt.Point;

import objects.BaseObject;

public class CompositionLink extends BaseLink {
    public CompositionLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        super(source, target, sourcePoint, targetPoint);
    }
}
