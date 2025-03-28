package forms;

import javax.swing.*;

import core.CanvasManager;
import core.DrawerManager;
import utils.DrawerUtil;
import constants.UMLConstants;

import java.awt.*;

public class Canvas extends JPanel {
    private final CanvasManager canvasManager = CanvasManager.getInstance();
    private final DrawerManager drawerManager = DrawerManager.getInstance();

    public Canvas() {
        setup();
    }

    public void setup() {
        setLayout(null);
        setBackground(UMLConstants.CANVAS_BG);

        canvasManager.setCanvas(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DrawerUtil.clear(this, g);

        drawerManager.drawAll(g);
    }
}
