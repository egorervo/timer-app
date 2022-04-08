package com.timer;

import com.timer.tools.GifSequenceWriter;
import com.timer.tools.ImageCreator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test.gif");
        ImageOutputStream ios = ImageIO.createImageOutputStream(fileOutputStream);

        ImageCreator imageCreator = new ImageCreator();

        GifSequenceWriter gifSequenceWriter = new GifSequenceWriter(ios, TYPE_INT_ARGB, 1000, true);
        gifSequenceWriter.writeToSequence(imageCreator.createImage("Just"));
        gifSequenceWriter.writeToSequence(imageCreator.createImage("Gif"));
        gifSequenceWriter.writeToSequence(imageCreator.createImage("Creation"));
        gifSequenceWriter.writeToSequence(imageCreator.createImage("Demo"));
        gifSequenceWriter.close();
        ios.close();
        fileOutputStream.close();
    }
}
