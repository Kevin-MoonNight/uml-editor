package modes;

import core.UMLManager;
import links.AssociationLink;
import links.BaseLink;
import links.CompositionLink;
import links.GeneralizationLink;
import links.LinkType;
import objects.BaseObject;

public class LinkMode implements Mode {
    private LinkType linkType;
    private BaseObject origin;
    private BaseObject destination;

    public LinkMode(LinkType linkType) {
        this.linkType = linkType;
    }

    public void setOrigin(BaseObject origin) {
        this.origin = origin;
    }

    public void setDestination(BaseObject destination) {
        this.destination = destination;
    }

    public void handle() {
        System.out.println("LinkMode");

        BaseLink link = null;

        switch (linkType) {
            case ASSOCIATION:
                link = new AssociationLink(origin, destination);
                break;
            case GENERALIZATION:
                link = new GeneralizationLink(origin, destination);
                break;
            case COMPOSITION:
                link = new CompositionLink(origin, destination);
                break;
            default:
                break;
        }

        UMLManager.getInstance().addLink(link);
    }
}
