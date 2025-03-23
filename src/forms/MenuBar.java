package forms;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar(Frame owner) {
        JMenu fileMenu = new JMenu("File");

        JMenu editMenu = new JMenu("Edit");
        createLabelMenuItem(owner, editMenu);

        this.add(fileMenu);
        this.add(editMenu);
    }

    private void createLabelMenuItem(Frame owner, JMenu editMenu) {
        JMenuItem label = new JMenuItem("Label");
        editMenu.add(label);

        label.addActionListener(e -> {
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
