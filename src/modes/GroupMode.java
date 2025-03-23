package modes;

import core.UMLManager;
import objects.BaseObject;
import objects.Composite;

public class GroupMode implements Mode {
    private BaseObject[] objects;

    public void setObjects(BaseObject[] objects) {
        this.objects = objects;
    }

    public void handle() {
        Composite composite = new Composite(objects);

        UMLManager.getInstance().addObject(composite);
    }
}
