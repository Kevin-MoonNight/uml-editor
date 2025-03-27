package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Objects;

import constants.UMLConstants;
import core.UMLManager;

import objects.BaseObject;
import objects.Boundary;
import objects.ObjectLabel;

public class LabelDrawer implements Drawable {
    private final UMLManager umlManager;

    public LabelDrawer(UMLManager umlManager) {
        Objects.requireNonNull(umlManager, "UMLManager cannot be null");

        this.umlManager = umlManager;
    }

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

        Boundary labelBoundary = calculateLabelBoundary(object.getBoundary());

        // Draw label shape
        if ("Rectangle".equals(label.getShape())) {
            drawRectangleLabel(g, label, labelBoundary);
        } else {
            drawOvalLabel(g, label, labelBoundary);
        }

        drawLabelText(g, label, labelBoundary);
    }

    private void drawLabelText(Graphics g, ObjectLabel label, Boundary labelBoundary) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, label.getFontSize()));
        FontMetrics metrics = g.getFontMetrics();
        String text = label.getName();

        // Calculate text position (centered in label)
        int textX = labelBoundary.getX() + (labelBoundary.getWidth() - metrics.stringWidth(text)) / 2;
        int textY = labelBoundary.getY() + (labelBoundary.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

        g.drawString(text, textX, textY);
    }

    private Boundary calculateLabelBoundary(Boundary objBoundary) {
        // Calculate label size
        int labelWidth = (int) (objBoundary.getWidth() * UMLConstants.LABEL_SIZE_RATIO);
        int labelHeight = (int) (objBoundary.getHeight() * UMLConstants.LABEL_SIZE_RATIO);

        // Calculate label position (centered)
        int labelX = objBoundary.getX() + (objBoundary.getWidth() - labelWidth) / 2;
        int labelY = objBoundary.getY() + (objBoundary.getHeight() - labelHeight) / 2;

        return new Boundary(labelX, labelY, labelWidth, labelHeight);
    }

    private void drawRectangleLabel(Graphics g, ObjectLabel label, Boundary labelBoundary) {
        g.setColor(label.getColor());

        g.fillRect(labelBoundary.getX(), labelBoundary.getY(), labelBoundary.getWidth(), labelBoundary.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(labelBoundary.getX(), labelBoundary.getY(), labelBoundary.getWidth(), labelBoundary.getHeight());
    }

    private void drawOvalLabel(Graphics g, ObjectLabel label, Boundary labelBoundary) {
        g.setColor(label.getColor());

        g.fillOval(labelBoundary.getX(), labelBoundary.getY(), labelBoundary.getWidth(), labelBoundary.getHeight());
        g.setColor(Color.BLACK);
        g.drawOval(labelBoundary.getX(), labelBoundary.getY(), labelBoundary.getWidth(), labelBoundary.getHeight());
    }
}
