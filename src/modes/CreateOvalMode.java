package modes;

import core.CanvasManager;
import core.UMLManager;
import objects.OvalObject;

public class CreateOvalMode extends CreateMode {
    public CreateOvalMode(CanvasManager canvasManager, UMLManager umlManager) {
        super(canvasManager, umlManager);
    }

    @Override
    public void handle() throws IllegalArgumentException {
        umlManager.addObject(new OvalObject(boundary));
    }
}
