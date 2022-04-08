package com.timer.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCreator {
    public BufferedImage createImage(String text) {
        int frameWith = 800;
        BufferedImage image = new BufferedImage(frameWith, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();  // not sure on this line, but this seems more right
        int textSize = 75;
        Font font = new Font("Arial", Font.BOLD, textSize);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, frameWith, 600); // give the whole image a white background
        graphics.setColor(Color.GREEN);
        graphics.drawString(text, calculateCenterX(text, textSize, frameWith), 300);
        graphics.dispose();
        return image;
    }

    private int calculateCenterX(String text, int textSize, int frameWith) {
        return text.length() * 10;
    }
}
