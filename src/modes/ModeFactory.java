package modes;

import core.UMLManager;

public class ModeFactory {
    private final UMLManager umlManager;

    public ModeFactory(UMLManager umlManager) {
        this.umlManager = umlManager;
    }

    public Mode createMode(UMLMode mode) {
        return switch (mode) {
            case SELECT -> new SelectMode(umlManager);
            case ASSOCIATION -> new AssociationLinkMode(umlManager);
            case GENERALIZATION -> new GeneralizationLinkMode(umlManager);
            case COMPOSITION -> new CompositionLinkMode(umlManager);
            case RECT -> new CreateRectMode(umlManager);
            case OVAL -> new CreateOvalMode(umlManager);
        };
    }
}