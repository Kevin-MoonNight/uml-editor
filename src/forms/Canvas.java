package forms;

import java.awt.Graphics;

import javax.swing.JPanel;

import constants.UMLConstants;
import core.CanvasManager;
import core.DrawerManager;
import utils.DrawerUtil;

public class Canvas extends JPanel {
    private final CanvasManager canvasManager = CanvasManager.getInstance();
    private final DrawerManager drawerManager = DrawerManager.getInstance();

    public Canvas() {
        configureCanvas();

        canvasManager.setCanvas(this);
    }

    public void configureCanvas() {
        setLayout(null);
        setBackground(UMLConstants.CANVAS_BG);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DrawerUtil.clear(this, g);

        drawerManager.drawAll(g);
    }
}
