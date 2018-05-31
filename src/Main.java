import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public void start (Stage menu) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button startButton = new Button();
        Text text = new Text();
        Scene scene = new Scene(grid, 600, 400);

        grid.add(text, 0, 0, 1, 1);
        text.setText("BUTTLESHIP");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setFill(Color.PINK);

        menu.setTitle("Buttleship");
        menu.setScene(scene);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(startButton);
        grid.add(hbBtn, 1, 4);
        startButton.setText("Start");
        startButton.setTextFill(Color.WHITE);
        startButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30) );
        startButton.setStyle("-fx-background-color: pink");
        startButton.setMinSize(100, 50);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Battleship.createNewGame();
                menu.close();
            }
        });
        menu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

