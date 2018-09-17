import Game.Quest;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void ReaderTest(){
        Quest quest = new Quest();
        Font rulesFont = new Font("TimesRoman", Font.PLAIN, 19);
        Box testReader = Box.createVerticalBox();
        quest.reader(testReader, "data\\environmentStart.txt");
        Box testText = Box.createVerticalBox();
        JLabel label1 = new JLabel("Вы по-прежнему не помните, как оказались взаперти.");
        label1.setFont(rulesFont);
        JLabel label2 = new JLabel("Тут темно и холодно. Из-за этого вам хочется поскорее выбраться");
        label2.setFont(rulesFont);
        testText.add(label1);
        testText.add(label2);
        assertEquals(label1.getFont(), testReader.getComponent(0).getFont());
    }
    @Test
    public void ButtonTest(){
        Quest quest = new Quest();
        Color buttonColor = new Color(233, 179, 97);
        Font BigFontTR = new Font("TimesRoman", Font.PLAIN, 26);
        JButton button = new JButton();
        JButton testButton = new JButton();
        quest.buttonInitialization(button);
        testButton.setForeground(buttonColor);
        testButton.setFont(BigFontTR);
        assertEquals(testButton.getColorModel(), button.getColorModel());
        assertEquals(testButton.getFont(), button.getFont());
    }
}
