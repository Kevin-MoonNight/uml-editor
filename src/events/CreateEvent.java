package events;

import java.awt.event.MouseEvent;

import modes.CreateMode;
import objects.Boundary;

public class CreateEvent {
    private static final int DEFAULT_SIZE = 100;

    public static void handle(MouseEvent e, CreateMode createMode) {
        createMode.setBoundary(new Boundary(e.getX(), e.getY(), DEFAULT_SIZE, DEFAULT_SIZE));
        createMode.handle();
    }
}
