import forms.UMLEditorForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        SwingUtilities.invokeLater(() -> {
            UMLEditorForm form = new UMLEditorForm();
            form.show();
//            JFrame frame = new JFrame("UML Editor");
//            UMLEditorForm form = new UMLEditorForm();
//            frame.setContentPane(form.getMainPanel());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
        });
    }
}