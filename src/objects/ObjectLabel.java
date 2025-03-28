package objects;

import java.awt.Color;

public class ObjectLabel {
    String name;
    String shape;
    Color color;
    int fontSize;

    public ObjectLabel(String name, String shape, Color color, int fontSize) {
        this.name = name;
        this.shape = shape;
        this.color = color;
        this.fontSize = fontSize;
    }

    public String getName() {
        return name;
    }

    public String getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShape(String object) {
        this.shape = object;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
