package events;

import java.util.List;

import core.UMLManager;
import objects.CompositeObject;
import objects.BaseObject;

public class UnGroupEvent {
    private static final UMLManager umlManager = UMLManager.getInstance();

    public static void handle() {
        var objects = umlManager.getSelectedObjects();

        if (objects.size() == 0) {
            return;
        }

        var compositeObjects = filterCompositeObjects(objects);

        umlManager.removeObjects(compositeObjects);

        unpackCompositeObjects(compositeObjects).forEach(umlManager::addObject);
    }

    private static List<BaseObject> filterCompositeObjects(List<BaseObject> objects) {
        return objects.stream()
                .filter(obj -> obj instanceof CompositeObject)
                .toList();
    }

    private static List<BaseObject> unpackCompositeObjects(List<BaseObject> compositeObjects) {
        return compositeObjects.stream()
                .map(obj -> ((CompositeObject) obj).getObjects())
                .flatMap(List::stream)
                .toList();
    }
}
