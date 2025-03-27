import forms.UMLEditorForm;

import javax.swing.*;

import core.CanvasManager;
import core.DrawerManager;
import core.ModeManager;

public class Main {
    public static void main(String[] args) {
        initializeUI();
    }

    private static void initializeUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            UMLEditorForm form = new UMLEditorForm();
            form.show();
        });

        ModeManager.getInstance().addModeChangeListener(CanvasManager.getInstance());
        ModeManager.getInstance().addModeChangeListener(DrawerManager.getInstance());
    }
}