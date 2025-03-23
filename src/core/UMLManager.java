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
    }

    public void removeObject(BaseObject object) {
        objects.remove(object);
    }

    public void addLink(BaseLink link) {
        links.add(link);
    }

    public void removeLink(BaseLink link) {
        links.remove(link);
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
