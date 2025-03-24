package events;

import java.util.List;

import core.UMLManager;
import objects.CompositeObject;

public class UnGroupEvent {
    public UnGroupEvent() {
    }

    public static void handle() {
        System.out.println("UnGroupEvent handled");

        // Get selected objects
        var objects = UMLManager.getInstance().getSelectedObjects();

        // Filter out composites
        var composites = objects.stream()
                .filter(obj -> obj instanceof CompositeObject)
                .map(obj -> (CompositeObject) obj)
                .toList();

        // Remove composites from UMLManager
        UMLManager.getInstance().removeObjects((List) composites);

        // Add objects inside composites to UMLManager
        composites.stream()
                .map(CompositeObject::getObjects)
                .flatMap(List::stream)
                .forEach(UMLManager.getInstance()::addObject);
    }
}
