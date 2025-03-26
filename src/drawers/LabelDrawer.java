package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import core.UMLManager;

import objects.BaseObject;
import objects.Boundary;
import objects.ObjectLabel;

public class LabelDrawer implements Drawable {
    private final UMLManager umlManager = UMLManager.getInstance();

    @Override
    public void draw(Graphics g) {
        umlManager.getObjects()
                .stream()
                .filter(object -> object.getLabel() != null)
                .forEach(object -> drawLabel(g, object));
    }

    private void drawLabel(Graphics g, BaseObject object) {
        ObjectLabel label = object.getLabel();
        if (label == null)
            return;

        Boundary objBoundary = object.getBoundary();

        // Calculate label size (80% of object size)
        int labelWidth = (int) (objBoundary.getWidth() * 0.8);
        int labelHeight = (int) (objBoundary.getHeight() * 0.8);

        // Calculate label position (centered)
        int labelX = objBoundary.getX() + (objBoundary.getWidth() - labelWidth) / 2;
        int labelY = objBoundary.getY() + (objBoundary.getHeight() - labelHeight) / 2;

        // Draw label shape
        g.setColor(label.getColor());
        if ("Rectangle".equals(label.getShape())) {
            g.fillRect(labelX, labelY, labelWidth, labelHeight);
            g.setColor(Color.BLACK);
            g.drawRect(labelX, labelY, labelWidth, labelHeight);
        } else { // Oval
            g.fillOval(labelX, labelY, labelWidth, labelHeight);
            g.setColor(Color.BLACK);
            g.drawOval(labelX, labelY, labelWidth, labelHeight);
        }

        // Draw text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, label.getFontSize()));
        FontMetrics metrics = g.getFontMetrics();
        String text = label.getName();

        // Calculate text position (centered in label)
        int textX = labelX + (labelWidth - metrics.stringWidth(text)) / 2;
        int textY = labelY + (labelHeight - metrics.getHeight()) / 2 + metrics.getAscent();

        g.drawString(text, textX, textY);
    }
}
