package modes;

import java.awt.Point;

import core.UMLManager;
import links.BaseLink;
import links.GeneralizationLink;
import objects.BaseObject;

public class GeneralizationLinkMode extends LinkMode {
    public GeneralizationLinkMode(UMLManager umlManager) {
        super(umlManager);
    }

    @Override
    protected BaseLink createLink(BaseObject source, BaseObject target, Point sourcePoint, Point targetPoint) {
        return new GeneralizationLink(source, target, sourcePoint, targetPoint);
    }
}
