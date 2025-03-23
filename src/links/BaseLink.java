package links;

import objects.BaseObject;

public class BaseLink {
    private BaseObject origin;
    private BaseObject destination;

    public BaseLink(BaseObject origin, BaseObject destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public BaseObject getOrigin() {
        return origin;
    }

    public BaseObject getDestination() {
        return destination;
    }

    public void setOrigin(BaseObject origin) {
        this.origin = origin;
    }

    public void setDestination(BaseObject destination) {
        this.destination = destination;
    }
}
