package forms;

import java.awt.Frame;

import javax.swing.JMenuItem;

import core.CanvasManager;
import events.GroupEvent;

public class GroupAction extends JMenuItem {

    public GroupAction(Frame owner) {
        super("Group");

        registerGroupEventListener();
    }

    private void registerGroupEventListener() {
        addActionListener(e -> {
            GroupEvent.handle();
            CanvasManager.getInstance().update();
        });
    }
}