package core;

import java.util.ArrayList;
import java.util.List;

import links.BaseLink;
import modes.Mode;
import objects.BaseObject;

public class UMLManager {
    private static UMLManager instance;

    private Mode mode;

    private List<BaseObject> objects = new ArrayList<>();
    private List<BaseLink> links = new ArrayList<>();
    private List<BaseObject> selectedObjects = new ArrayList<>();

    private UMLManager() {
    }

    public static UMLManager getInstance() {
        if (instance == null) {
            instance = new UMLManager();
        }
        return instance;
    }

    public List<BaseObject> getObjects() {
        return objects;
    }

    public List<BaseLink> getLinks() {
        return links;
    }

    public void addObject(BaseObject object) {
        objects.add(object);
        CanvasManager.getInstance().update();
    }

    public void removeObject(BaseObject object) {
        objects.remove(object);
        CanvasManager.getInstance().update();
    }

    public void removeObject(List<BaseObject> objects) {
        for (BaseObject object : objects) {
            this.objects.remove(object);
        }
        CanvasManager.getInstance().update();
    }

    public void addLink(BaseLink link) {
        links.add(link);
        CanvasManager.getInstance().update();
    }

    public void removeLink(BaseLink link) {
        links.remove(link);
        CanvasManager.getInstance().update();
    }

    public List<BaseObject> getSelectedObjects() {
        return selectedObjects;
    }

    public void setSelectedObjects(List<BaseObject> selectedObjects) {
        this.selectedObjects.clear();
        this.selectedObjects.addAll(selectedObjects);
        CanvasManager.getInstance().update();
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void reset() {
        objects.clear();
        links.clear();
    }
}
