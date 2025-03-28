package forms;

import javax.swing.*;

import objects.ObjectLabel;

import java.awt.*;
import java.util.Map;

public class CustomLabelStyleDialog extends JDialog {
    private static final String[] SHAPE_OPTIONS = { "Rectangle", "Oval" };
    private static final String[] COLOR_OPTIONS = { "Gray", "Red", "Yellow", "Green" };
    private static final Integer[] FONT_SIZE_OPTIONS = { 8, 10, 12, 14, 16, 18, 20, 24 };
    private static final Map<String, Color> COLOR_MAP = Map.of(
            "Gray", Color.LIGHT_GRAY, "Red", Color.RED, "Yellow", Color.YELLOW, "Green", Color.GREEN);

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

    public CustomLabelStyleDialog(Frame owner, ObjectLabel existingLabel) {
        super(owner, "Custom Label Style", true);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        // 設定預設值
        if (existingLabel != null) {
            nameField.setText(existingLabel.getName());
        }

        JLabel shapeLabel = new JLabel("Shape:");
        shapeBox = new JComboBox<>(SHAPE_OPTIONS);
        if (existingLabel != null) {
            shapeBox.setSelectedItem(existingLabel.getShape());
        }

        JLabel colorLabel = new JLabel("Color:");
        colorBox = new JComboBox<>(COLOR_OPTIONS);
        if (existingLabel != null) {
            for (Map.Entry<String, Color> entry : COLOR_MAP.entrySet()) {
                if (entry.getValue().equals(existingLabel.getColor())) {
                    colorBox.setSelectedItem(entry.getKey());
                    break;
                }
            }
        }

        JLabel fontSizeLabel = new JLabel("Font Size:");
        fontSizeBox = new JComboBox<>(FONT_SIZE_OPTIONS);
        if (existingLabel != null) {
            fontSizeBox.setSelectedItem(existingLabel.getFontSize());
        }

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

    public Color getSelectedColor() {
        var colorName = (String) colorBox.getSelectedItem();

        return COLOR_MAP.get(colorName);
    }

    public int getSelectedFontSize() {
        return (Integer) fontSizeBox.getSelectedItem();
    }
}
