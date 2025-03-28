package modes;

import core.UMLManager;
import objects.OvalObject;

public class CreateOvalMode extends CreateMode {
    public CreateOvalMode(UMLManager umlManager) {
        super(umlManager);
    }

    @Override
    public void handle() {
        umlManager.addObject(new OvalObject(getBoundary()));
    }
}
