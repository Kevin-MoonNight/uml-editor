package modes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.UMLManager;
import events.CreateEvent;
import objects.Boundary;

public abstract class CreateMode implements Mode {
    protected UMLManager umlManager;

    private Boundary boundary;

    public CreateMode(UMLManager umlManager) {
        this.umlManager = umlManager;
    }

    public MouseAdapter getTrigger() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CreateEvent.handle(e, CreateMode.this);
            }
        };
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public Boundary getBoundary() {
        return boundary;
    }
}
