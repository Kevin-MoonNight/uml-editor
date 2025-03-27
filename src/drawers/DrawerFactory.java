package drawers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import core.CanvasManager;
import core.UMLManager;

public class DrawerFactory {
    private final UMLManager umlManager;
    private final CanvasManager canvasManager;

    public DrawerFactory(UMLManager umlManager, CanvasManager canvasManager) {
        Objects.requireNonNull(umlManager, "UMLManager cannot be null");
        Objects.requireNonNull(canvasManager, "CanvasManager cannot be null");

        this.umlManager = umlManager;
        this.canvasManager = canvasManager;
    }

    public Drawable createDrawer(DrawerType type) {
        return switch (type) {
            case LABEL -> new LabelDrawer(umlManager);
            case OBJECT -> new ObjectDrawer(umlManager);
            case LINK -> new LinkDrawer(umlManager);
            case SELECT -> new SelectBoxDrawer(umlManager);
            case LINKING -> new LinkingDrawer(umlManager, canvasManager);
        };
    }

    public List<Drawable> createDefaultDrawers() {
        return Arrays.asList(
                createDrawer(DrawerType.OBJECT),
                createDrawer(DrawerType.LINK),
                createDrawer(DrawerType.LABEL));
    }
}
