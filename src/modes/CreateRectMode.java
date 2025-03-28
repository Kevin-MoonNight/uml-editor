package modes;

import core.UMLManager;
import objects.RectObject;

public class CreateRectMode extends CreateMode {
    public CreateRectMode(UMLManager umlManager) {
        super(umlManager);
    }

    @Override
    public void handle() {
        umlManager.addObject(new RectObject(getBoundary()));
    }
}
