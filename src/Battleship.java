import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Battleship extends Application {

    @Override
    public void start(Stage windowOfComputer) throws Exception {
        Group root = new Group();
        windowOfComputer.setTitle("Battleship");
        windowOfComputer.setScene(new Scene(root, 600, 600, Color.WHITE));
        Canvas canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);
        GraphicsContext lines = canvas.getGraphicsContext2D();
        int sizeOfCell = 60;
        for (int i = 1; i < 10; i ++) {
            lines.beginPath();
            lines.setStroke(Color.LIGHTBLUE);
            lines.moveTo(0, i*sizeOfCell);
            lines.lineTo(10*sizeOfCell, i*sizeOfCell);
            lines.stroke();
            lines.moveTo(i*sizeOfCell, 0);
            lines.lineTo(i*sizeOfCell, 10*sizeOfCell);
            lines.stroke();
        }
        windowOfComputer.show();
    }
}
