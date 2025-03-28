package forms;

import java.awt.Frame;

import javax.swing.JMenuItem;
import java.awt.Color;

import core.CanvasManager;
import core.UMLManager;
import objects.CompositeObject;
import objects.ObjectLabel;

public class LabelAction extends JMenuItem {
    public LabelAction(Frame owner) {
        super("Label");

        addActionListener(e -> {
            var objs = UMLManager.getInstance().getSelectedObjects();
            if (objs.size() != 1) {
                return;
            }

            var obj = objs.get(0);

            if (obj instanceof CompositeObject) {
                return;
            }

            CustomLabelStyleDialog dialog = new CustomLabelStyleDialog(owner, obj.getLabel());
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                // Handle the dialog result here
                String name = dialog.getName();
                String shape = dialog.getSelectedShape();
                Color color = dialog.getSelectedColor();
                int fontSize = dialog.getSelectedFontSize();

                var label = new ObjectLabel(name, shape, color, fontSize);
                obj.setLabel(label);
                CanvasManager.getInstance().update();
            }
        });
    }
}
