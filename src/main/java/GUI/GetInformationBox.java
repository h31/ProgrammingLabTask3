package GUI;

import javax.swing.*;
import java.awt.*;

public class GetInformationBox extends JFrame
{
    // Текстовые поля
    JTextField starName, starRadius;

    Button ok;

    String name;

    int radius;

    private void initListeners() {
        ok.addActionListener(e -> {
            name = starName.getText();
            radius = Integer.parseInt(starRadius.getText());
        });
    }

    public GetInformationBox() {
        super("Add a Star");
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel starNameLabel = new JLabel("Name of star");
        contents.add(starNameLabel);
        starName = new JTextField(15);
        contents.add(starName);
        JLabel starRadiusLabel = new JLabel("Radius of star");
        contents.add(starRadiusLabel);
        starRadius = new JTextField(15);
        contents.add(starRadius);
        ok = new Button("OK");
        contents.add(ok);
        setContentPane(contents);
        setSize(200, 100);
        setVisible(true);
        initListeners();
    }

    public static void main(String[] args) {
        new GetInformationBox();
    }
}