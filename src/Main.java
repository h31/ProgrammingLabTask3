import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 610, 558);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeper");
        scene.setFill(Color.DARKGRAY);
        VBox vBox = new VBox();
        Pane gameArea = new Pane();
        MenuBar menuBar = new MenuBar();
        Menu game = new Menu("Game");
        MenuItem restart = new MenuItem("Restart");
        MenuItem changeFieldSize = new MenuItem("Change field size");
        MenuItem exit = new MenuItem("Exit");
        menuBar.getMenus().add(game);
        menuBar.setMinSize(610, 30);
        game.getItems().add(restart);
        game.getItems().add(changeFieldSize);
        game.getItems().add(exit);
        vBox.getChildren().addAll(menuBar, gameArea);
        root.getChildren().add(vBox);
        Minesweeper minesweeper = new Minesweeper(10, 30, 50);
        restart.setOnAction(event -> {
            minesweeper.restart();
        });
        exit.setOnAction(event -> System.exit(0));
        changeFieldSize.setOnAction(event -> {
            List<Integer> params = getFieldSize();
            if (params == null) return;
            minesweeper.setGameParams(params.get(0), params.get(1), params.get(2));
            minesweeper.restart();
            primaryStage.setWidth(10 + params.get(0) * 60);
            primaryStage.setHeight(68 + 17 * params.get(1));
            menuBar.setMinWidth(70 + params.get(0) * 60);
        });
        Controller.game = minesweeper;
        Controller.area = gameArea;
        Controller.init(10, 30);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private List<Integer> getFieldSize() {
        Dialog<List<Integer>> dialog = new Dialog<>();
        dialog.setTitle("Change field size");
        dialog.setHeaderText("Please enter height, width and mines count");
        dialog.setResizable(false);
        Label widthLabel = new Label("Width:");
        Label heightLabel = new Label("Height:");
        Label minesCountLabel = new Label("Mines:");
        TextField widthTxt = new TextField();
        TextField heightTxt = new TextField();
        TextField minesCountTxt = new TextField();
        GridPane grid = new GridPane();
        grid.add(widthLabel, 1, 1);
        grid.add(heightLabel, 1, 2);
        grid.add(minesCountLabel, 1, 3);
        grid.add(widthTxt, 2, 1);
        grid.add(heightTxt, 2, 2);
        grid.add(minesCountTxt, 2, 3);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);
        dialog.setResultConverter(new Callback<ButtonType, List<Integer>>() {
            @Override
            public List<Integer> call(ButtonType param) {
                if (param == buttonOk) {
                    List<Integer> result = new ArrayList<>();
                    if (widthTxt.getText() == null || heightTxt.getText() == null || minesCountTxt.getText() == null)
                        return null;
                    result.add(Integer.valueOf(widthTxt.getText()));
                    result.add(Integer.valueOf(heightTxt.getText()));
                    result.add(Integer.valueOf(minesCountTxt.getText()));
                    return result;
                }
                return null;
            }
        });
        Optional result = dialog.showAndWait();
        if (result.isPresent()) return (List<Integer>) result.get();
        else return null;
    }
}
