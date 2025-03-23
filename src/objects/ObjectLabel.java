package objects;

import java.awt.Color;

public class ObjectLabel {
    String name;
    String object;
    Color color;
    int fontSize;

    public ObjectLabel(String name, String object, Color color, int fontSize) {
        this.name = name;
        this.object = object;
        this.color = color;
        this.fontSize = fontSize;
    }
}
