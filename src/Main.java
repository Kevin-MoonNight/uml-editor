import forms.UMLEditorForm;

import javax.swing.*;

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
    }
}