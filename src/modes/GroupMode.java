package modes;

import java.util.List;

import core.UMLManager;
import objects.BaseObject;
import objects.Composite;

public class GroupMode implements Mode {
    private List<BaseObject> objects;

    public void setObjects(List<BaseObject> objects) {
        this.objects = objects;
    }

    public void handle() {
        Composite composite = new Composite(objects);

        UMLManager.getInstance().removeObject(objects);
        UMLManager.getInstance().addObject(composite);
    }
}
