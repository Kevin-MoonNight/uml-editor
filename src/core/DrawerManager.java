package core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drawers.Drawable;
import drawers.DrawerType;
import drawers.LabelDrawer;
import drawers.LinkDrawer;
import drawers.LinkingDrawer;
import drawers.NullDrawer;
import drawers.ObjectDrawer;
import drawers.SelectBoxDrawer;
import modes.AssociationLinkMode;
import modes.CompositionLinkMode;
import modes.CreateOvalMode;
import modes.CreateRectMode;
import modes.GeneralizationLinkMode;
import modes.Mode;
import modes.SelectMode;

public class DrawerManager implements ModeChangeListener {
    private final UMLManager umlManager = UMLManager.getInstance();
    private final CanvasManager canvasManager = CanvasManager.getInstance();
    private final ModeManager modeManager = ModeManager.getInstance();
    private final List<Drawable> DEFAULT_DRAWERS = createDefaultDrawers();
    private List<Drawable> drawers = Collections.synchronizedList(new ArrayList<>(DEFAULT_DRAWERS));

    public static final Map<Class<? extends Mode>, DrawerType> MODE_DRAWER_MAP = new HashMap<>();
    static {
        MODE_DRAWER_MAP.put(SelectMode.class, DrawerType.SELECT);
        MODE_DRAWER_MAP.put(AssociationLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(GeneralizationLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(CompositionLinkMode.class, DrawerType.LINKING);
        MODE_DRAWER_MAP.put(CreateRectMode.class, DrawerType.NONE);
        MODE_DRAWER_MAP.put(CreateOvalMode.class, DrawerType.NONE);
    }

    private DrawerManager() {
    }

    private static class Holder {
        private static final DrawerManager INSTANCE = new DrawerManager();
    }

    public static DrawerManager getInstance() {
        return Holder.INSTANCE;
    }

    private Drawable createDrawer(DrawerType type) {
        return switch (type) {
            case LABEL -> new LabelDrawer(umlManager);
            case OBJECT -> new ObjectDrawer(umlManager);
            case LINK -> new LinkDrawer(umlManager);
            case SELECT -> new SelectBoxDrawer(modeManager);
            case LINKING -> new LinkingDrawer(modeManager, canvasManager);
            case NONE -> new NullDrawer();
        };
    }

    private List<Drawable> createDefaultDrawers() {
        return Arrays.asList(
                createDrawer(DrawerType.OBJECT),
                createDrawer(DrawerType.LINK),
                createDrawer(DrawerType.LABEL));
    }

    public void registerCustomDrawer(Drawable drawer) {
        drawers.clear();
        drawers.addAll(DEFAULT_DRAWERS);

        if (drawer != null) {
            drawers.add(drawer);
        }
    }

    @Override
    public void onModeChanged(Mode oldMode, Mode newMode) {
        var drawer = createDrawer(MODE_DRAWER_MAP.get(newMode.getClass()));

        registerCustomDrawer(drawer);
    }

    public void drawAll(Graphics g) {
        drawers.forEach(drawer -> drawer.draw(g));
    }
}
