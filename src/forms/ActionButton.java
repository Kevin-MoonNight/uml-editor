package forms;

import constants.UMLConstants;
import utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionButton extends JButton {
    private ImageIcon originalIcon;

    public ActionButton(String name) {
        setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(true);
        setFocusPainted(false);
        setToolTipText(name);
        setFocusable(true);
        setBackground(UMLConstants.BUTTON_DEFAULT_BG);
        setForeground(UMLConstants.BUTTON_DEFAULT_FG);

        loadIcon(name);
        setupListeners();
    }

    private void loadIcon(String name) {
        try {
            originalIcon = new ImageIcon("icons/" + name + ".png");
            setIcon(IconUtil.createColorizedIcon(originalIcon, UMLConstants.BUTTON_DEFAULT_FG));
        } catch (Exception e) {
            System.err.println("無法加載圖標: " + name);
        }
    }

    private void setupListeners() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                updateColors(UMLConstants.BUTTON_FOCUSED_BG, UMLConstants.BUTTON_FOCUSED_FG);
            }

            @Override
            public void focusLost(FocusEvent e) {
                updateColors(UMLConstants.BUTTON_DEFAULT_BG, UMLConstants.BUTTON_DEFAULT_FG);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!hasFocus()) {
                    updateColors(UMLConstants.BUTTON_HOVER_BG, UMLConstants.BUTTON_HOVER_FG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!hasFocus()) {
                    updateColors(UMLConstants.BUTTON_DEFAULT_BG, UMLConstants.BUTTON_DEFAULT_FG);
                }
            }
        });
    }

    private void updateColors(Color bg, Color fg) {
        setBackground(bg);
        setForeground(fg);
        setIcon(IconUtil.createColorizedIcon(originalIcon, fg));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}
