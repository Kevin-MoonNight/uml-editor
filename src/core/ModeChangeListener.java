package core;

import modes.Mode;

public interface ModeChangeListener {
    void onModeChanged(Mode oldMode, Mode newMode);
}
