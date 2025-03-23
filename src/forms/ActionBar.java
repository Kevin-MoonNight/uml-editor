package forms;

import javax.swing.*;
import java.awt.*;

public class ActionBar extends JToolBar {
    private static final String[] actions = {
            "Select", "Association", "Generalization", "Composition", "Rect", "Oval"
    };

    private static final Dimension size = new Dimension(100, Integer.MAX_VALUE);

    public ActionBar() {
        setup();
        createActionButtons();
    }

    private void setup() {
        setLayout(new GridLayout(actions.length, 1, 10, 10));
        setPreferredSize(size);
        setFloatable(false);
    }

    private void createActionButtons() {
        for (String action : actions) {
            ActionButton btn = new ActionButton(action);
            add(btn);
        }
    }
}
