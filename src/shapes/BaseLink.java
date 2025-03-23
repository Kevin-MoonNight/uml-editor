package shapes;

public class BaseLink {
    private BaseShape origin;
    private BaseShape destination;

    public BaseLink(BaseShape origin, BaseShape destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public BaseShape getOrigin() {
        return origin;
    }

    public BaseShape getDestination() {
        return destination;
    }

    public void setOrigin(BaseShape origin) {
        this.origin = origin;
    }

    public void setDestination(BaseShape destination) {
        this.destination = destination;
    }
}
