package modes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.CanvasManager;
import core.UMLManager;
import objects.Boundary;

public abstract class CreateMode implements Mode {
    protected Boundary boundary;
    protected CanvasManager canvasManager;
    protected UMLManager umlManager;

    public CreateMode(CanvasManager canvasManager, UMLManager umlManager) {
        this.canvasManager = canvasManager;
        this.umlManager = umlManager;
        setup();
    }

    private void setup() {
        var trigger = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boundary = new Boundary(e.getX(), e.getY(), 100, 100);

                handle();

                canvasManager.update();
            }
        };

        canvasManager.addMouseListener(trigger);
    }

    public abstract void handle();
}
