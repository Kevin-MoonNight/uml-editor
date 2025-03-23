package forms;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    public Canvas() {
        setup();
    }

    private void setup() {
        setLayout(null);
        setBackground(Color.lightGray);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
    }
}
