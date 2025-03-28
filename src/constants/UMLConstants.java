package constants;

import modes.UMLMode;
import java.awt.Color;

public final class UMLConstants {
    private UMLConstants() {
    }

    // Core constants
    public static final int OBJECT_SIZE = 100;
    public static final UMLMode DEFAULT_ACTION = UMLMode.SELECT;
    public static final double LABEL_SIZE_RATIO = 0.8;

    // Button colors
    public static final Color BUTTON_DEFAULT_BG = Color.WHITE;
    public static final Color BUTTON_DEFAULT_FG = Color.BLACK;
    public static final Color BUTTON_FOCUSED_BG = Color.BLACK;
    public static final Color BUTTON_FOCUSED_FG = Color.WHITE;
    public static final Color BUTTON_HOVER_BG = Color.LIGHT_GRAY;
    public static final Color BUTTON_HOVER_FG = Color.BLACK;

    // Canvas settings
    public static final Color CANVAS_BG = Color.LIGHT_GRAY;

    // Window settings
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
}
