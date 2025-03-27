package drawers;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

import core.ModeManager;
import modes.SelectMode;
import objects.Boundary;

public class SelectBoxDrawer implements Drawable {
    private final ModeManager modeManager;

    public SelectBoxDrawer(ModeManager modeManager) {
        Objects.requireNonNull(modeManager, "ModeManager cannot be null");

        this.modeManager = modeManager;
    }

    @Override
    public void draw(Graphics g) {
        SelectMode selectMode = (SelectMode) modeManager.getMode();
        var origin = selectMode.getSource();
        var destination = selectMode.getTarget();

        if (origin != null && destination != null) {
            var boundary = new Boundary(
                    Math.min(origin.x, destination.x),
                    Math.min(origin.y, destination.y),
                    Math.abs(destination.x - origin.x),
                    Math.abs(destination.y - origin.y));
            drawSelectBox(g, boundary);
        }
    }

    private void drawSelectBox(Graphics g, Boundary b) {
        int x = b.getX();
        int y = b.getY();
        int width = b.getWidth();
        int height = b.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        // 保存原始設置
        Composite originalComposite = g2d.getComposite();

        // 設置半透明度 (0.3f 表示 30% 的不透明度)
        g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.3f));
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, width, height);

        // 恢復原始設置
        g2d.setComposite(originalComposite);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);
    }
}
