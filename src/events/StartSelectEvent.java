package events;

import java.awt.event.MouseEvent;

import core.UMLManager;
import modes.SelectMode;
import utils.BoundaryUtil;

public class StartSelectEvent {
    private static UMLManager umlManager = UMLManager.getInstance();

    public static void handle(MouseEvent e, SelectMode selectMode) {
        selectMode.setLastMousePosition(e.getPoint());
        selectMode.setSource(e.getPoint());

        var targetObject = BoundaryUtil.getObjectAtPoint(umlManager.getObjects(),
                e.getPoint());

        if (targetObject == null) {
            umlManager.clearSelectedObjects();
            return;
        }

        umlManager.upperObject(targetObject);

        if (umlManager.isSelected(targetObject)) {
            return;
        }

        umlManager.updateSelectedObjects(targetObject);
    }
}
