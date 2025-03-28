package forms;

import java.awt.Frame;

import javax.swing.JMenuItem;

import core.CanvasManager;
import events.UnGroupEvent;

public class UnGroupAction extends JMenuItem {
    public UnGroupAction(Frame owner) {
        super("UnGroup");

        registerUnGroupEventListener();
    }

    private void registerUnGroupEventListener() {
        addActionListener(e -> {
            UnGroupEvent.handle();
            CanvasManager.getInstance().update();
        });
    }
}
