package drawers;

import java.util.Arrays;
import java.util.List;

public class DrawerFactory {
    public static Drawable createDrawer(DrawerType type) {
        return switch (type) {
            case LABEL -> new LabelDrawer();
            case OBJECT -> new ObjectDrawer();
            case LINK -> new LinkDrawer();
            case SELECT -> new SelectBoxDrawer();
            case LINKING -> new LinkingDrawer();
        };
    }

    public static List<Drawable> createDefaultDrawers() {
        return Arrays.asList(
                createDrawer(DrawerType.OBJECT),
                createDrawer(DrawerType.LINK),
                createDrawer(DrawerType.LABEL));
    }
}
