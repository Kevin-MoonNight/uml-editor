package forms;

import javax.swing.*;
import java.awt.*;

public class UMLEditorForm {
    private final JFrame frame = new JFrame("UML Editor");
    private final Canvas canvas = new Canvas();
    private final MenuBar menubar = new MenuBar(frame);
    private final ActionBar actionBar = new ActionBar();

    public UMLEditorForm() {
        setUpFrame();

        setupMainPanel();
    }

    private void setupMainPanel() {
        JPanel mainContentPanel = new JPanel(new BorderLayout(10, 0));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContentPanel.add(actionBar, BorderLayout.WEST);
        mainContentPanel.add(canvas, BorderLayout.CENTER);

        frame.add(mainContentPanel);
    }

    private void setUpFrame() {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setJMenuBar(menubar);
    }

    public void show() {
        frame.setVisible(true);
    }
}
