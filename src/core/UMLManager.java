package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import links.BaseLink;
import modes.Mode;
import objects.BaseObject;

public class UMLManager {
    private Mode mode;

    private final List<BaseObject> objects = Collections.synchronizedList(new ArrayList<>());
    private final List<BaseLink> links = Collections.synchronizedList(new ArrayList<>());
    private final List<BaseObject> selectedObjects = Collections.synchronizedList(new ArrayList<>());

    private UMLManager() {
    }

    private static class Holder {
        private static final UMLManager INSTANCE = new UMLManager();
    }

    public static UMLManager getInstance() {
        return Holder.INSTANCE;
    }

    public List<BaseObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    public List<BaseLink> getLinks() {
        return Collections.unmodifiableList(links);
    }

    public void addObject(BaseObject object) {
        Objects.requireNonNull(object, "Object cannot be null");

        if (objects.contains(object)) {
            return;
        }

        objects.add(object);
    }

    public void removeObjects(List<BaseObject> objects) {
        this.objects.removeAll(objects);

        selectedObjects.removeAll(objects);
    }

    public void addLink(BaseLink link) {
        links.add(link);
    }

    public List<BaseObject> getSelectedObjects() {
        return Collections.unmodifiableList(selectedObjects);
    }

    public void upperObject(BaseObject object) {
        Objects.requireNonNull(object, "Object cannot be null");

        if (!objects.contains(object)) {
            return;
        }

        objects.remove(object);
        objects.add(object);
    }

    public void updateSelectedObjects(BaseObject... objects) {
        updateSelectedObjects(List.of(objects));
    }

    public void updateSelectedObjects(List<BaseObject> objects) {
        Objects.requireNonNull(objects, "Objects cannot be null");

        synchronized (this) {
            objects.forEach(this::upperObject);
            selectedObjects.clear();
            selectedObjects.addAll(objects);
        }
    }

    public boolean isSelected(BaseObject object) {
        return selectedObjects.contains(object);
    }

    public void clearSelectedObjects() {
        selectedObjects.clear();
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = Objects.requireNonNull(mode, "Mode cannot be null");
    }
}
