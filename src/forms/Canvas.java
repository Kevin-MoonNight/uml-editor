package forms;

import javax.swing.*;

import core.CanvasManager;
import core.DrawerManager;
import utils.DrawerUtil;

import java.awt.*;

public class Canvas extends JPanel {
    private final CanvasManager canvasManager = CanvasManager.getInstance();
    private final DrawerManager drawerManager = DrawerManager.getInstance();

    public Canvas() {
        setup();
    }

    public void setup() {
        setLayout(null);
        setBackground(Color.lightGray);

        canvasManager.setCanvas(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DrawerUtil.clear(this, g);

        drawerManager.drawAll(g);
    }
}
