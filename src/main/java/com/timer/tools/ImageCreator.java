package com.timer.tools;

import com.timer.model.Dimensions;
import com.timer.model.GifDimensions;
import com.timer.model.ImageDimensions;
import com.timer.model.ImageTextParams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageCreator {

    public static final int GENERAL_PADDING = 20;
    private static final int GENERAL_TOP_PADDING = 20;

    public BufferedImage createImage(String timeText, int textSize) {
        Font font = new Font("Arial", Font.PLAIN, textSize);
        Dimensions dimensions = getDimensions(font, timeText, GENERAL_PADDING, GENERAL_TOP_PADDING);

        BufferedImage image = new BufferedImage(dimensions.getWidth(), dimensions.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0, 0, dimensions.getWidth(), dimensions.getHeight());
        graphics.setColor(Color.GREEN);
        graphics.drawString(timeText, GENERAL_PADDING / 2, dimensions.getHeight() - GENERAL_TOP_PADDING * 2);
        graphics.dispose();
        return image;
    }

    public List<BufferedImage> createImage(List<ImageTextParams> params, int textSize) {
        Font font = new Font("Arial", Font.PLAIN, textSize);
        GifDimensions gifDimension = getGifDimension(params, font);
        List<BufferedImage> images = new ArrayList<>();

        for (int i = 0; i < params.size(); i++) {
            ImageTextParams imageTextParams = params.get(i);
            ImageDimensions imageDimensions = gifDimension.getDimensions().get(i);

            BufferedImage image = new BufferedImage(gifDimension.getMaxWidth(), gifDimension.getMaxHeight() + 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setFont(font);
            graphics.setColor(Color.BLUE);
            graphics.fillRect(0, 0, gifDimension.getMaxWidth(), gifDimension.getMaxHeight() + 20);

            graphics.setColor(Color.GREEN);
            int x = 0;

            if (imageTextParams.isIncludeDays()) {
                graphics.drawString(imageTextParams.getDays(), x, gifDimension.getMaxHeight() / 2);
                graphics.drawString(imageTextParams.getLabelDays(), x, gifDimension.getMaxHeight());

                x += Math.max(imageDimensions.getDaysDimensions().getWidth(),
                        imageDimensions.getDaysLabelDimensions().getWidth());

                graphics.drawString(imageTextParams.getSeparator(), x,gifDimension.getMaxHeight() / 2);
                x += imageDimensions.getSeparatorDimensions().getWidth();
            }

            graphics.drawString(imageTextParams.getHours(), x, gifDimension.getMaxHeight() / 2);
            graphics.drawString(imageTextParams.getLabelHours(), x, gifDimension.getMaxHeight());

            x += Math.max(imageDimensions.getHoursDimensions().getWidth(),
                    imageDimensions.getHoursLabelDimensions().getWidth());

            graphics.drawString(imageTextParams.getSeparator(), x,gifDimension.getMaxHeight() / 2);
            x += imageDimensions.getSeparatorDimensions().getWidth();

            graphics.drawString(imageTextParams.getMinutes(), x, gifDimension.getMaxHeight() / 2);
            graphics.drawString(imageTextParams.getLabelMinute(), x, gifDimension.getMaxHeight());

            x += Math.max(imageDimensions.getMinutesDimensions().getWidth(),
                    imageDimensions.getMinutesLabelDimensions().getWidth());

            graphics.drawString(imageTextParams.getSeparator(), x,gifDimension.getMaxHeight() / 2);
            x += imageDimensions.getSeparatorDimensions().getWidth();

            graphics.drawString(imageTextParams.getSeconds(), x, gifDimension.getMaxHeight() / 2);
            graphics.drawString(imageTextParams.getLabelSeconds(), x, gifDimension.getMaxHeight());

            graphics.dispose();
            images.add(image);
        }


        return images;
    }

    private GifDimensions getGifDimension(List<ImageTextParams> params, Font font) {
        int maxWidth = 0;
        int maxHeight = 0;
        GifDimensions res = new GifDimensions();
        res.setDimensions(new ArrayList<>());
        for (ImageTextParams imageTextParams : params) {
            Dimensions separatorDimensions = getDimensions(font, imageTextParams.getSeparator());
            Dimensions daysDimensions = getDimensions(font, imageTextParams.getDays());
            Dimensions hoursDimensions = getDimensions(font, imageTextParams.getHours());
            Dimensions minutesDimensions = getDimensions(font, imageTextParams.getMinutes());
            Dimensions secondsDimensions = getDimensions(font, imageTextParams.getSeconds());
            Dimensions daysLabelDimensions = getDimensions(font, imageTextParams.getLabelDays());
            Dimensions hoursLabelDimensions = getDimensions(font, imageTextParams.getLabelHours());
            Dimensions minutesLabelDimensions = getDimensions(font, imageTextParams.getLabelMinute());
            Dimensions secondsLabelDimensions = getDimensions(font, imageTextParams.getLabelSeconds());
            maxHeight = evaluateMaxHeight(maxHeight,
                    separatorDimensions,
                    daysDimensions,
                    hoursDimensions,
                    minutesDimensions,
                    secondsDimensions,
                    daysLabelDimensions,
                    hoursLabelDimensions,
                    minutesLabelDimensions,
                    secondsLabelDimensions);
            int w = daysDimensions.getWidth()
                    + separatorDimensions.getWidth() * (imageTextParams.isIncludeDays() ? 3 : 2)
                    + hoursDimensions.getWidth()
                    + minutesDimensions.getWidth()
                    + secondsDimensions.getWidth();

            int wl = daysLabelDimensions.getWidth()
                    + hoursLabelDimensions.getWidth()
                    + minutesLabelDimensions.getWidth()
                    + secondsLabelDimensions.getWidth();
            if (w > maxWidth) {
                maxWidth = w;
            }
            if (wl > maxWidth) {
                maxWidth = wl;
            }
            res.getDimensions().add(ImageDimensions.builder()
                            .separatorDimensions(separatorDimensions)
                            .daysDimensions(daysDimensions)
                            .hoursDimensions(hoursDimensions)
                            .minutesDimensions(minutesDimensions)
                            .secondsDimensions(secondsDimensions)
                            .daysLabelDimensions(daysLabelDimensions)
                            .hoursLabelDimensions(hoursLabelDimensions)
                            .minutesLabelDimensions(minutesLabelDimensions)
                            .secondsLabelDimensions(secondsLabelDimensions)
                    .build());
        }
        res.setMaxHeight(maxHeight + 45);
        res.setMaxWidth(maxWidth + 120);
        return res;
    }

    private int evaluateMaxHeight(int maxHeight,
                                  Dimensions separatorDimensions,
                                  Dimensions daysDimensions,
                                  Dimensions hoursDimensions,
                                  Dimensions minutesDimensions,
                                  Dimensions secondsDimensions,
                                  Dimensions daysLabelDimensions,
                                  Dimensions hoursLabelDimensions,
                                  Dimensions minutesLabelDimensions,
                                  Dimensions secondsLabelDimensions) {
        maxHeight = Math.max(separatorDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(daysDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(hoursDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(minutesDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(secondsDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(daysLabelDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(hoursLabelDimensions.getHeight(), maxHeight);
        maxHeight = Math.max(minutesLabelDimensions.getHeight(), maxHeight);
        return Math.max(secondsLabelDimensions.getHeight(), maxHeight);
    }


    private Dimensions getDimensions(Font font, String text) {
        return getDimensions(font, text, 0, 0);
    }

    private Dimensions getDimensions(Font font, String text, int horizontalMargin, int verticalMargin) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        int frameWidth = (int) font.getStringBounds(text, frc).getWidth() + horizontalMargin;
        int frameHeight = (int) font.getStringBounds(text, frc).getHeight() + verticalMargin;
        return new Dimensions(frameWidth, frameHeight);
    }

}
