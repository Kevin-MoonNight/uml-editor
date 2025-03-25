package modes;

import core.CanvasManager;
import core.UMLManager;
import objects.RectObject;

public class CreateRectMode extends CreateMode {
    public CreateRectMode(CanvasManager canvasManager, UMLManager umlManager) {
        super(canvasManager, umlManager);
    }

    @Override
    public void handle() throws IllegalArgumentException {
        umlManager.addObject(new RectObject(boundary));
    }
}
