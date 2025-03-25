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

        // Create a composite object
        var composite = new CompositeObject(selectedObjects);

        // Remove objects from UMLManager
        umlManager.removeObjects(selectedObjects);
        umlManager.clearSelectedObjects();

        // Add composite object to UMLManager
        umlManager.addObject(composite);
        umlManager.updateSelectedObjects(composite);
    }
}
