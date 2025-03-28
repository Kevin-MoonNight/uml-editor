package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IconUtil {
    public static ImageIcon createColorizedIcon(ImageIcon originalIcon, Color color) {
        if (originalIcon == null)
            return null;

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

        return new ImageIcon(buffered);
    }
}