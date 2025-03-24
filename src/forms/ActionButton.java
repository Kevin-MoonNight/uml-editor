package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionButton extends JButton {
    private static final Color DEFAULT_BG = Color.WHITE;
    private static final Color DEFAULT_FG = Color.BLACK;
    private static final Color FOCUSED_BG = Color.BLACK;
    private static final Color FOCUSED_FG = Color.WHITE;
    private static final Color HOVER_BG = Color.LIGHT_GRAY;
    private static final Color HOVER_FG = Color.BLACK;

    public ActionButton(String name) {
        setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(true);
        setFocusPainted(false);
        setToolTipText(name);
        setFocusable(true);
        setBackground(DEFAULT_BG);
        setForeground(DEFAULT_FG);
        setText(name);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(FOCUSED_BG);
                setForeground(FOCUSED_FG);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(DEFAULT_BG);
                setForeground(DEFAULT_FG);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_BG);
                setForeground(HOVER_FG);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!hasFocus()) {
                    setBackground(DEFAULT_BG);
                    setForeground(DEFAULT_FG);
                }
            }
        });
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
