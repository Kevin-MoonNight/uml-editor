package events;

import core.UMLManager;
import objects.CompositeObject;

public class GroupEvent {
    public GroupEvent() {
    }

    public static void handle() {
        System.out.println("GroupEvent handled");

        // Get selected objects
        var objects = UMLManager.getInstance().getSelectedObjects();

        // Create a composite object
        var composite = new CompositeObject(objects);

        // Remove objects from UMLManager
        UMLManager.getInstance().removeObject(objects);
        UMLManager.getInstance().getSelectedObjects().clear();

        // Add composite object to UMLManager
        UMLManager.getInstance().addObject(composite);
        UMLManager.getInstance().getSelectedObjects().add(composite);
    }
}
