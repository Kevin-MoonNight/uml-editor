package forms;

import javax.swing.*;
import java.awt.*;

public class CustomLabelStyleDialog extends JDialog {
    private static final String[] SHAPE_OPTIONS = { "Rectangle", "Oval" };
    private static final String[] COLOR_OPTIONS = { "Black" };
    private static final Integer[] FONT_SIZE_OPTIONS = { 8, 10, 12, 14, 16, 18, 20, 24 };

    private JTextField nameField;
    private JComboBox<String> shapeBox;
    private JComboBox<String> colorBox;
    private JComboBox<Integer> fontSizeBox;
    private boolean confirmed = false;

    public CustomLabelStyleDialog(Frame owner) {
        super(owner, "Custom Label Style", true);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel shapeLabel = new JLabel("Shape:");
        shapeBox = new JComboBox<>(SHAPE_OPTIONS);

        JLabel colorLabel = new JLabel("Color:");
        colorBox = new JComboBox<>(COLOR_OPTIONS);

        JLabel fontSizeLabel = new JLabel("Font Size:");
        fontSizeBox = new JComboBox<>(FONT_SIZE_OPTIONS);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // Layout setup
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(shapeLabel);
        mainPanel.add(shapeBox);
        mainPanel.add(colorLabel);
        mainPanel.add(colorBox);
        mainPanel.add(fontSizeLabel);
        mainPanel.add(fontSizeBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getName() {
        return nameField.getText();
    }

    public String getSelectedShape() {
        return (String) shapeBox.getSelectedItem();
    }

    public String getSelectedColor() {
        return (String) colorBox.getSelectedItem();
    }

    public int getSelectedFontSize() {
        return (Integer) fontSizeBox.getSelectedItem();
    }
}
