package core;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import constants.UMLConstants;
import modes.Mode;
import modes.ModeFactory;
import modes.UMLMode;

public class ModeManager {
    private final UMLManager umlManager = UMLManager.getInstance();
    private final ModeFactory modeFactory = new ModeFactory(umlManager);
    private final Map<UMLMode, Mode> modes = createModes();
    private final List<ModeChangeListener> listeners = Collections.synchronizedList(new ArrayList<>());
    private Mode currentMode = changeMode(UMLConstants.DEFAULT_ACTION);

    private ModeManager() {
    }

    private static class Holder {
        private static final ModeManager INSTANCE = new ModeManager();
    }

    public static ModeManager getInstance() {
        return Holder.INSTANCE;
    }

    private Map<UMLMode, Mode> createModes() {
        Map<UMLMode, Mode> newModes = new EnumMap<>(UMLMode.class);

        for (UMLMode mode : UMLMode.values()) {
            newModes.put(mode, modeFactory.createMode(mode));
        }

        return Collections.unmodifiableMap(newModes);
    }

    public Mode changeMode(UMLMode mode) {
        var newMode = modes.get(mode);
        var oldMode = this.currentMode;

        this.currentMode = newMode;

        // Notify all listeners
        listeners.forEach(listener -> listener.onModeChanged(oldMode, newMode));

        return newMode;
    }

    public Mode getMode() {
        return this.currentMode;
    }

    public void addModeChangeListener(ModeChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeModeChangeListener(ModeChangeListener listener) {
        this.listeners.remove(listener);
    }
}
