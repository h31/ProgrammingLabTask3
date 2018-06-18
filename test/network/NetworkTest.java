package network;

import logic.image.ImagePreprocessor;
import logic.network.Network;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkTest {

    private String commonPath = "/Users/sergey/Desktop/imageForEducation/";

    @Test
    void checkNormalNumber() throws IOException {
        int allNumbers = 10 * 50;
        int allCorrectAnswers = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50; j++) {
                BufferedImage image = ImageIO.read(new File(String.format("%s%d/%d.png", commonPath, i, j)));
                Network network = new Network(image);
                if (i == network.getMostLikelyResult()) allCorrectAnswers++;
            }
        }
        double percentIncorrectAnswers = (double) (100 * (allNumbers - allCorrectAnswers)) / allNumbers;
        System.out.println("Percent of error = " + percentIncorrectAnswers);
        assertTrue(percentIncorrectAnswers < 15);
    }
}
