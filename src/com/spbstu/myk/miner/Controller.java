package com.spbstu.myk.miner;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    public SimpleIntegerProperty size = new SimpleIntegerProperty();
    private int[][] minercard;
    @FXML
    private AnchorPane mainCol;

    public Controller(int size) {
        this.size.set(size);
    }

    private Polygon newGex() {
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(33.7, 24.5,
                12.0, 37.0,
                -9.7, 24.5,
                -9.7, -0.5,
                12.0, -13.0,
                33.7, -0.5);
        hexagon.setFill(Color.AQUA);
        hexagon.setStroke(Color.grayRgb(0));
        hexagon.setOnMouseClicked(this::onClick);
        return hexagon;
    }

    @FXML
    private void initialize() {
        for (int i = 0; i < size.get(); i++) {
            int ofset = i % 2 != 1 ? 0 : 22;
            for (int j = 0; j < size.get(); j++) {
                StackPane polyg = new StackPane();
                Polygon p = newGex();
                p.setId("" + i + "" + j);
                polyg.getChildren().add(p);
                polyg.relocate(j * 43 + ofset, i * 38);
                mainCol.getChildren().add(polyg);
            }
        }
    }

    private int getNextRandom() {
        return ThreadLocalRandom.current().nextInt(0, size.get());
    }

    private void genCard(int total, int x, int y) {
        int size = this.size.get();
        minercard = new int[size][size];
        HashSet<Point> map = new HashSet<>();
        map.add(new Point(x, y));
        for (int i = 0; i < total; i++) {
            Point tmp = new Point(getNextRandom(), getNextRandom());
            while (map.contains(tmp)) tmp = new Point(getNextRandom(), getNextRandom());
            map.add(tmp);
            craft(tmp);
        }
    }

    private void craft(Point min) {
        minercard[min.y][min.x] = -1;
        if (new Point(min.x + 1, min.y).valid())
            minercard[min.y][min.x + 1] = minercard[min.y][min.x + 1] == -1 ? -1 : minercard[min.y][min.x + 1] + 1;
        if (new Point(min.x - 1, min.y).valid())
            minercard[min.y][min.x - 1] = minercard[min.y][min.x - 1] == -1 ? -1 : minercard[min.y][min.x - 1] + 1;
        if (min.y % 2 != 0) {
            if (new Point(min.x + 1, min.y + 1).valid())
                minercard[min.y + 1][min.x + 1] = minercard[min.y + 1][min.x + 1] == -1 ? -1 : minercard[min.y + 1][min.x + 1] + 1;
            if (new Point(min.x, min.y + 1).valid())
                minercard[min.y + 1][min.x] = minercard[min.y + 1][min.x] == -1 ? -1 : minercard[min.y + 1][min.x] + 1;
            if (new Point(min.x + 1, min.y - 1).valid())
                minercard[min.y - 1][min.x + 1] = minercard[min.y - 1][min.x + 1] == -1 ? -1 : minercard[min.y - 1][min.x + 1] + 1;
            if (new Point(min.x, min.y - 1).valid())
                minercard[min.y - 1][min.x] = minercard[min.y - 1][min.x] == -1 ? -1 : minercard[min.y - 1][min.x] + 1;
        } else {
            if (new Point(min.x - 1, min.y + 1).valid())
                minercard[min.y + 1][min.x - 1] = minercard[min.y + 1][min.x - 1] == -1 ? -1 : minercard[min.y + 1][min.x - 1] + 1;
            if (new Point(min.x, min.y + 1).valid())
                minercard[min.y + 1][min.x] = minercard[min.y + 1][min.x] == -1 ? -1 : minercard[min.y + 1][min.x] + 1;
            if (new Point(min.x - 1, min.y - 1).valid())
                minercard[min.y - 1][min.x - 1] = minercard[min.y - 1][min.x - 1] == -1 ? -1 : minercard[min.y - 1][min.x - 1] + 1;
            if (new Point(min.x, min.y - 1).valid())
                minercard[min.y - 1][min.x] = minercard[min.y - 1][min.x] == -1 ? -1 : minercard[min.y - 1][min.x] + 1;
        }
    }

    @FXML
    private void onClick(MouseEvent event) {
        int x = Integer.valueOf(String.valueOf((((Polygon) event.getSource()).getId().charAt(0))));
        int y = Integer.valueOf(String.valueOf((((Polygon) event.getSource()).getId().charAt(1))));
        if (minercard == null)
            genCard(10, x, y);
        if (minercard[x][y] == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("You Lose!");
            alert.setHeaderText("");
            alert.setContentText("Sorry, but tou exploded \uD83D\uDE14");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }
        Text txt = genLabel(minercard[x][y]);
        Polygon p = ((Polygon) event.getSource());
        p.setFill(Color.gray(0.8));
        p.setOnMouseClicked(null);
        (((StackPane) p.getParent())).getChildren().add(txt);
    }

    private Text genLabel(int i) {
        Text txt = new Text();
        txt.setText(i == 0 ? "" : String.valueOf(i));
        txt.setStyle("-fx-font-weight: bold");
        if (i == 1) txt.setFill(Color.BLUE);
        if (i == 2) txt.setFill(Color.GREEN);
        if (i == 3) txt.setFill(Color.RED);
        if (i == 4) txt.setFill(Color.DARKBLUE);
        if (i == 5) txt.setFill(Color.PURPLE);
        if (i == 6) txt.setFill(Color.ORANGERED);
        return txt;
    }

    private Text genLabel(int i, String str) {
        Text res = genLabel(i);
        res.setText(str);
        return res;
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return this.x < 8 && this.x > -1 && this.y < 9 && this.y > -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
