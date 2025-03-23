package forms;

import javax.swing.*;

import java.awt.*;

public class ActionButton extends JButton {
    public ActionButton(String name) {
        setToolTipText(name);
        setFocusable(false);
        setBackground(Color.WHITE);
        setToolTipText(name);
        setText(name);
    }
}
