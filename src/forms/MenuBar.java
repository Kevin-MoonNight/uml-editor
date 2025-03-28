package forms;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar(Frame owner) {
        JMenu fileMenu = new JMenu("File");

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new LabelAction(owner));
        editMenu.add(new GroupAction(owner));
        editMenu.add(new UnGroupAction(owner));

        this.add(fileMenu);
        this.add(editMenu);
    }
}
