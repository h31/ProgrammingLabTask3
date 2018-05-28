package com.spbstu.myk.miner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    public int size = 8;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextInputDialog dialog = new TextInputDialog("8");
        dialog.setTitle("New game");
        dialog.setHeaderText("Hello. Some TMT?)");
        dialog.setContentText("Please, enter number of rows");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ssize -> size = Integer.valueOf(ssize));
        FXMLLoader r = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller c = new Controller(size);
        r.setController(c);
        Parent root = r.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, (size + 2) * 38 - 12, size * 43 - 22));
        primaryStage.getProperties().put("size", size);
        primaryStage.show();
    }
}
