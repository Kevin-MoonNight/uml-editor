package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import links.LinkType;
import modes.CreateOvalMode;
import modes.CreateRectMode;
import modes.LinkMode;
import modes.SelectMode;

import java.awt.*;

public class ActionBar extends JToolBar {
    private static final String[] actions = {
            "Select",
            "Association",
            "Generalization",
            "Composition",
            "Rect",
            "Oval"
    };

    private static final String DEFAULT_ACTION = "Select";

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
            btn.addActionListener(e -> handleAction(action));

            if (action.equals(DEFAULT_ACTION)) {
                btn.requestFocus();
            }

            add(btn);
        }
    }

    private void handleAction(String action) {
        System.out.println("Action: " + action);

        CanvasManager.getInstance().clearMouseListeners();
        ;
        CanvasManager.getInstance().getCanvas().setup();

        switch (action) {
            case "Select":
                UMLManager.getInstance().setMode(new SelectMode());
                break;
            case "Association":
                UMLManager.getInstance().setMode(new LinkMode(LinkType.ASSOCIATION));
                break;
            case "Generalization":
                UMLManager.getInstance().setMode(new LinkMode(LinkType.GENERALIZATION));
                break;
            case "Composition":
                UMLManager.getInstance().setMode(new LinkMode(LinkType.COMPOSITION));
                break;
            case "Rect":
                UMLManager.getInstance()
                        .setMode(new CreateRectMode(CanvasManager.getInstance(), UMLManager.getInstance()));
                break;
            case "Oval":
                UMLManager.getInstance()
                        .setMode(new CreateOvalMode(CanvasManager.getInstance(), UMLManager.getInstance()));
                break;
            default:
                break;
        }
    }
}
