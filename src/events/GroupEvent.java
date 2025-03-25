package events;

import core.UMLManager;
import objects.CompositeObject;

public class GroupEvent {
    private static final UMLManager umlManager = UMLManager.getInstance();

    public static void handle() {
        var selectedObjects = umlManager.getSelectedObjects();

        if (selectedObjects.size() <= 1) {
            return;
        }

        var composite = new CompositeObject(selectedObjects);

        umlManager.removeObjects(selectedObjects);
        umlManager.addObject(composite);

        umlManager.updateSelectedObjects(composite);
    }
}
