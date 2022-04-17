package com.timer;

import com.timer.model.ImageTextParams;
import com.timer.tools.CountdownTextGenerator;
import com.timer.tools.GifSequenceWriter;
import com.timer.tools.ImageCreator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test1.gif");
        ImageOutputStream ios = ImageIO.createImageOutputStream(fileOutputStream);

        ImageCreator imageCreator = new ImageCreator();

        int textSize = 55;
        List<ImageTextParams> getCountDownTextParams = new CountdownTextGenerator().getCountDownTextParams(
                LocalDateTime.now().plus(20, ChronoUnit.DAYS).plus(20, ChronoUnit.SECONDS),
                60, true, true, ":");
        List<BufferedImage> image = imageCreator.createImage(getCountDownTextParams, textSize);

        GifSequenceWriter gifSequenceWriter = new GifSequenceWriter(ios, TYPE_INT_ARGB, 1000, false);

        image.forEach(i -> {
            try {
                gifSequenceWriter.writeToSequence(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gifSequenceWriter.close();
        ios.close();
        fileOutputStream.close();
    }
}
