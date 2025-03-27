package modes;

public enum UMLMode {
    SELECT("Select"),
    ASSOCIATION("Association"),
    GENERALIZATION("Generalization"),
    COMPOSITION("Composition"),
    RECT("Rect"),
    OVAL("Oval");

    private final String mode;

    UMLMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
