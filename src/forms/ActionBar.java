package forms;

import javax.swing.*;

import core.UMLManager;
import links.LinkType;
import modes.CreateMode;
import modes.LinkMode;
import modes.SelectMode;
import objects.ObjectType;

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
            add(btn);
        }
    }

    private void handleAction(String action) {
        System.out.println("Action: " + action);

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
                UMLManager.getInstance().setMode(new CreateMode(ObjectType.RECT));
                break;
            case "Oval":
                UMLManager.getInstance().setMode(new CreateMode(ObjectType.OVAL));
                break;
            default:
                break;
        }
    }
}
