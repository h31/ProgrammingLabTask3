import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Battleship extends Application {

    boolean gameOver;
    AllShips shipsOfComputer;
    AllShots shotsOfComputer;
    AllShips shipsOfUser;
    AllShots shotsOfUser;
    Random randomShot;

    void distribution() {
        shipsOfComputer = new AllShips(60, true);
        shotsOfComputer = new AllShots(60);
        shipsOfUser = new AllShips(60, false);
        shotsOfUser = new AllShots(60);
        gameOver = false;
    }

    @Override
    public void start(Stage windowOfComputer) throws Exception {
        Group root = new Group();
        windowOfComputer.setTitle("Battleship");
        windowOfComputer.setScene(new Scene(root, 600, 600, Color.WHITE));
        Canvas canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);
        GraphicsContext lines = canvas.getGraphicsContext2D();
        int sizeOfCell = 60;
        for (int i = 1; i < 10; i++) {
            lines.beginPath();
            lines.setStroke(Color.LIGHTBLUE);
            lines.moveTo(0, i * sizeOfCell);
            lines.lineTo(10 * sizeOfCell, i * sizeOfCell);
            lines.stroke();
            lines.moveTo(i * sizeOfCell, 0);
            lines.lineTo(i * sizeOfCell, 10 * sizeOfCell);
            lines.stroke();
        }
        windowOfComputer.show();
    }

    public void click(MouseEvent event) {
        this.click(event);
        int x = (int) event.getX() / 60;
        int y = (int) event.getY() / 60;
        if (event.getButton() == MouseButton.PRIMARY && !gameOver) {
            if (!shotsOfUser.hitSamePlace(x, y)) {
                shotsOfUser.addShot(x, y, true);
                if (shipsOfComputer.checkHit(x, y)) {
                    if (!shipsOfComputer.checkAlive()) {
                        gameOver = true; // say User win
                    }
                }
            }
            else computerShot(); 
        }

    }
    public void computerShot() {

    }

}
