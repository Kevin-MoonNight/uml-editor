package forms;

import javax.swing.*;

import core.CanvasManager;
import core.UMLManager;
import drawers.Drawable;
import drawers.DrawerFactory;
import drawers.DrawerType;
import modes.AssociationLinkMode;
import modes.CompositionLinkMode;
import modes.CreateOvalMode;
import modes.CreateRectMode;
import modes.GeneralizationLinkMode;
import modes.Mode;
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

    private final UMLManager umlManager = UMLManager.getInstance();
    private final CanvasManager canvasManager = CanvasManager.getInstance();
    private final DrawerFactory drawerFactory = new DrawerFactory(umlManager, canvasManager);

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

        Mode mode = null;
        Drawable customerDrawer = null;

        switch (action) {
            case "Select":
                mode = new SelectMode(umlManager);
                customerDrawer = drawerFactory.createDrawer(DrawerType.SELECT);
                break;
            case "Association":
                mode = new AssociationLinkMode(umlManager);
                customerDrawer = drawerFactory.createDrawer(DrawerType.LINKING);
                break;
            case "Generalization":
                mode = new GeneralizationLinkMode(umlManager);
                customerDrawer = drawerFactory.createDrawer(DrawerType.LINKING);
                break;
            case "Composition":
                mode = new CompositionLinkMode(umlManager);
                customerDrawer = drawerFactory.createDrawer(DrawerType.LINKING);
                break;
            case "Rect":
                mode = new CreateRectMode(umlManager);
                break;
            case "Oval":
                mode = new CreateOvalMode(umlManager);
                break;
            default:
                break;
        }

        umlManager.setMode(mode);
        canvasManager.registerTrigger(mode.getTrigger());
        canvasManager.registerCustomDrawer(customerDrawer);
    }
}
