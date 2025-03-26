package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ActionButton extends JButton {
    private static final Color DEFAULT_BG = Color.WHITE;
    private static final Color DEFAULT_FG = Color.BLACK;
    private static final Color FOCUSED_BG = Color.BLACK;
    private static final Color FOCUSED_FG = Color.WHITE;
    private static final Color HOVER_BG = Color.LIGHT_GRAY;
    private static final Color HOVER_FG = Color.BLACK;
    private ImageIcon originalIcon;

    public ActionButton(String name) {
        setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(true);
        setFocusPainted(false);
        setToolTipText(name);
        setFocusable(true);
        setBackground(DEFAULT_BG);
        setForeground(DEFAULT_FG);

        try {
            String iconPath = "icons/" + name + ".png";
            originalIcon = new ImageIcon(iconPath);
            updateIconColor(DEFAULT_FG);
        } catch (Exception e) {
            System.err.println("無法加載圖標: " + name);
        }

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(FOCUSED_BG);
                setForeground(FOCUSED_FG);
                updateIconColor(FOCUSED_FG);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(DEFAULT_BG);
                setForeground(DEFAULT_FG);
                updateIconColor(DEFAULT_FG);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_BG);
                setForeground(HOVER_FG);
                updateIconColor(HOVER_FG);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!hasFocus()) {
                    setBackground(DEFAULT_BG);
                    setForeground(DEFAULT_FG);
                    updateIconColor(DEFAULT_FG);
                }
            }
        });
    }

    private void updateIconColor(Color color) {
        if (originalIcon != null) {
            Image image = originalIcon.getImage();
            BufferedImage buffered = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = buffered.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            for (int x = 0; x < buffered.getWidth(); x++) {
                for (int y = 0; y < buffered.getHeight(); y++) {
                    int rgba = buffered.getRGB(x, y);
                    int alpha = (rgba >> 24) & 0xff;
                    if (alpha != 0) {
                        buffered.setRGB(x, y, new Color(
                                color.getRed(),
                                color.getGreen(),
                                color.getBlue(),
                                alpha).getRGB());
                    }
                }
            }

            setIcon(new ImageIcon(buffered));
        }
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
