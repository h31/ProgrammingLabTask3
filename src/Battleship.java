import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Battleship extends Application {

    public void start(Stage computerStage) {
        GridPane root = new GridPane();
        Scene scene = new Scene (root, 600, 600);
        computerStage.setTitle("Buttleship");
        computerStage.setScene(scene);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                cell.getStylesheets().add(Battleship.class.getResource("/StyleForBattleship.css").toExternalForm());
                cell.setMinSize(60, 60);
                cell.setOnMouseClicked(event -> {
                    cell.setDisable(false);
                    cell.setStyle("-fx-background-color: rgba(34, 85, 85, 0.13);");
                });
                cell.setCellType(CellType.COMMON);
                root.add(cell, i, j);
            }
        }
        computerStage.show();
    }
}
