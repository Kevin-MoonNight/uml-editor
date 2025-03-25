package modes;

import java.awt.Point;

import core.UMLManager;
import links.BaseLink;
import links.CompositionLink;
import objects.BaseObject;

public class CompositionLinkMode extends LinkMode {
    public CompositionLinkMode(UMLManager umlManager) {
        super(umlManager);
    }

    @Override
    protected BaseLink createLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        return new CompositionLink(source, target, sourcePoint, targetPoint);
    }
}
