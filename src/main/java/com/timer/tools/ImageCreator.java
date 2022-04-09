package com.timer.tools;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageCreator {

    public static final int GENERAL_PADDING = 20;
    private static final int GENERAL_TOP_PADDING = 10;

    public BufferedImage createImage(String text, int textSize) {
        Font font = new Font("Arial", Font.PLAIN, textSize);
        Dimensions dimensions = getDimensions(font, text);

        BufferedImage image = new BufferedImage(dimensions.getWidth(), dimensions.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();  // not sure on this line, but this seems more right
        graphics.setFont(font);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0, 0, dimensions.getWidth(), dimensions.getHeight()); // give the whole image a white background
        graphics.setColor(Color.GREEN);
        graphics.drawString(text, GENERAL_PADDING / 2, dimensions.getHeight() - GENERAL_TOP_PADDING);
        graphics.dispose();
        return image;
    }

    private Dimensions getDimensions(Font font, String text) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        int frameWidth = (int) font.getStringBounds(text, frc).getWidth() + GENERAL_PADDING;
        int frameHeight = (int) font.getStringBounds(text, frc).getHeight() + GENERAL_TOP_PADDING;
        return new Dimensions(frameWidth, frameHeight);
    }

    @Data
    @AllArgsConstructor
    private static class Dimensions {
        int width;
        int height;
    }
}
