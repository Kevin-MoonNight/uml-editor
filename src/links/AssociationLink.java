package links;

import java.awt.Point;

import objects.BaseObject;

public class AssociationLink extends BaseLink {
    public AssociationLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        super(source, target, sourcePoint, targetPoint);
    }
}
