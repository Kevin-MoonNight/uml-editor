package forms;

import javax.swing.*;

import constants.UMLConstants;
import core.ModeManager;
import modes.UMLMode;

import java.awt.*;

public class ActionBar extends JToolBar {
    private final ModeManager modeManager = ModeManager.getInstance();

    public ActionBar() {
        setup();
        createActionButtons();
    }

    private void setup() {
        setLayout(new GridLayout(UMLMode.values().length, 1, 10, 10));
        setPreferredSize(new Dimension(100, Integer.MAX_VALUE));
        setFloatable(false);
    }

    private void createActionButtons() {
        for (UMLMode mode : UMLMode.values()) {
            ActionButton btn = new ActionButton(mode.getMode());
            btn.addActionListener(e -> handleAction(mode));

            if (mode.equals(UMLConstants.DEFAULT_ACTION)) {
                btn.requestFocus();
            }

            add(btn);
        }
    }

    private void handleAction(UMLMode mode) {
        modeManager.changeMode(mode);
    }
}
