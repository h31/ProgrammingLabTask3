package planetSystem;

import javax.swing.*;
import java.util.List;

public class InfoPanel extends JPanel {

Star star;

List<Planet> planets;

    public InfoPanel() {
        super();
        // Выбор размещения в одну колонку
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Создание и добавление компонентов
    }
}