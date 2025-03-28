package events;

import java.awt.event.MouseEvent;

import core.UMLManager;
import modes.LinkMode;
import utils.BoundaryUtil;

public class StartLinkEvent {
    private static final UMLManager umlManager = UMLManager.getInstance();

    public static void handle(MouseEvent e, LinkMode linkMode) {
        var targetObject = BoundaryUtil.getObjectAtPoint(umlManager.getObjects(), e.getPoint());

        if (targetObject == null) {
            return;
        }

        linkMode.setSource(targetObject);
        linkMode.setSourcePoint(e.getPoint());
    }
}
