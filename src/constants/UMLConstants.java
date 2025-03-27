package constants;

import java.util.HashMap;
import java.util.Map;

import drawers.DrawerType;
import modes.AssociationLinkMode;
import modes.CompositionLinkMode;
import modes.CreateOvalMode;
import modes.CreateRectMode;
import modes.GeneralizationLinkMode;
import modes.Mode;
import modes.SelectMode;
import modes.UMLMode;

public final class UMLConstants {
    private UMLConstants() {
    }

    public static final int OBJECT_SIZE = 100;
    public static final UMLMode DEFAULT_ACTION = UMLMode.SELECT;

    public static final Map<Class<? extends Mode>, DrawerType> MODE_DRAWER_MAP = new HashMap<>();
    static {
        MODE_DRAWER_MAP.put(SelectMode.class, DrawerType.SELECT);
        MODE_DRAWER_MAP.put(AssociationLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(GeneralizationLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(CompositionLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(CreateRectMode.class, DrawerType.NONE);
        MODE_DRAWER_MAP.put(CreateOvalMode.class, DrawerType.NONE);
    }
}
