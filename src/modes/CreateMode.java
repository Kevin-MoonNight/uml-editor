package modes;

import core.UMLManager;
import objects.BaseObject;
import objects.Boundary;
import objects.ObjectType;
import objects.OvalObject;
import objects.RectObject;

public class CreateMode implements Mode {
    private ObjectType objectType;
    private Boundary boundary;

    public CreateMode(ObjectType objectType) {
        this.objectType = objectType;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public void handle() throws IllegalArgumentException {
        BaseObject newObject = null;

        switch (objectType) {
            case RECT:
                newObject = new RectObject(boundary);
                break;
            case OVAL:
                newObject = new OvalObject(boundary);
                break;
            default:
                throw new IllegalArgumentException("Invalid object type");
        }

        UMLManager.getInstance().addObject(newObject);
    }
}
