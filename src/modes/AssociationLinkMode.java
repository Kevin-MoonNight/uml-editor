package modes;

import java.awt.Point;

import core.UMLManager;
import links.AssociationLink;
import links.BaseLink;
import objects.BaseObject;

public class AssociationLinkMode extends LinkMode {
    public AssociationLinkMode(UMLManager umlManager) {
        super(umlManager);
    }

    @Override
    protected BaseLink createLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        return new AssociationLink(source, target, sourcePoint, targetPoint);
    }
}
