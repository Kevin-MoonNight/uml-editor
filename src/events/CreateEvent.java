package events;

import java.awt.event.MouseEvent;

import constants.UMLConstants;
import modes.CreateMode;
import objects.Boundary;

public class CreateEvent {
    public static void handle(MouseEvent e, CreateMode createMode) {
        createMode.setBoundary(new Boundary(e.getX(), e.getY(), UMLConstants.OBJECT_SIZE, UMLConstants.OBJECT_SIZE));
        createMode.handle();
    }
}
