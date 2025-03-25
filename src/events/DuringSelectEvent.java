package events;

import java.awt.Point;
import java.awt.event.MouseEvent;

import core.UMLManager;
import links.BaseLink;
import modes.SelectMode;
import utils.BoundaryUtil;

public class DuringSelectEvent {
    private static UMLManager umlManager = UMLManager.getInstance();

    public static void handle(MouseEvent e, SelectMode selectMode) {
        var selectedObjects = umlManager.getSelectedObjects();

        if (selectedObjects.size() == 0) {
            selectMode.setTarget(e.getPoint());
            return;
        }

        selectMode.setIsDragging(true);

        var offset = calculateOffset(selectMode.getLastMousePosition(), e.getPoint());
        BoundaryUtil.moveObjects(selectedObjects, offset);

        umlManager.getLinks().forEach(BaseLink::updateConnectionPoints);

        selectMode.setLastMousePosition(e.getPoint());
    }

    private static Point calculateOffset(Point source, Point target) {
        return new Point(target.x - source.x, target.y - source.y);
    }
}
