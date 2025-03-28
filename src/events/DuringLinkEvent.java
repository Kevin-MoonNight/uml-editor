package events;

import java.awt.event.MouseEvent;

import modes.LinkMode;

public class DuringLinkEvent {
    public static void handle(MouseEvent e, LinkMode linkMode) {
        linkMode.setTargetPoint(e.getPoint());
    }
}
