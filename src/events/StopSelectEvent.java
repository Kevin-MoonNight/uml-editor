package events;

import java.awt.event.MouseEvent;

import modes.SelectMode;

public class StopSelectEvent {
    public static void handle(MouseEvent e, SelectMode selectMode) {
        if (selectMode.getIsDragging()) {
            selectMode.reset();
            return;
        }

        selectMode.setTarget(e.getPoint());
        selectMode.handle();
    }
}
