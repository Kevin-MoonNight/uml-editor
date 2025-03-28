package events;

import java.awt.event.MouseEvent;

import core.UMLManager;
import modes.LinkMode;
import utils.BoundaryUtil;

public class StopLinkEvent {
    private static final UMLManager umlManager = UMLManager.getInstance();

    public static void handle(MouseEvent e, LinkMode linkMode) {
        var targetObject = BoundaryUtil.getObjectAtPoint(umlManager.getObjects(), e.getPoint());

        linkMode.setTarget(targetObject);
        linkMode.setTargetPoint(e.getPoint());

        linkMode.handle();
    }
}
