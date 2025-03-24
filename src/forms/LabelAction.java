package forms;

import java.awt.Frame;

import javax.swing.JMenuItem;

public class LabelAction extends JMenuItem {
    public LabelAction(Frame owner) {
        super("Label");

        addActionListener(e -> {
            CustomLabelStyleDialog dialog = new CustomLabelStyleDialog(owner);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                // Handle the dialog result here
                String name = dialog.getName();
                String shape = dialog.getSelectedShape();
                String color = dialog.getSelectedColor();
                int fontSize = dialog.getSelectedFontSize();
            }
        });
    }
}
