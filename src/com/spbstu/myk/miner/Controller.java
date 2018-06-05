package com.spbstu.myk.miner;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
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
    HashSet<Point> points = new HashSet<>();
    int mines = 0;
    double width = 0;
    double height = 0;

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
            double ofset = i % 2 != 1 ? 0 : 24;
            for (int j = 0; j < size.get(); j++) {
                StackPane polyg = new StackPane();
                Polygon p = newGex();
                p.setId("" + i + "" + j);
                polyg.getChildren().add(p);
                //polyg.relocate(64 + j * 55.5 + ofset, 5 + i * 46);
                polyg.relocate(j * 44.5 + ofset, i * 38);
                mainCol.getChildren().add(polyg);
            }
        }
    }

    private void resize(double k) {
        double c = 25 * k;
        if (c < 25) {
            c = 25;
            k = 1;
        }
        double R = c;
        double r = 0.866 * c;
        for (int i = 0; i < size.get(); i++) {
            double o = i % 2 == 0 ? 0 : r;
            for (int j = 0; j < size.get(); j++) {
                StackPane st = (StackPane) mainCol.getChildren().get(i * size.get() + j);
                st.relocate(j * r * 2 + o, 5 * k + i * 38 * k);
                Polygon p = (Polygon) st.getChildren().get(0);
                p.getPoints().clear();
                p.getPoints().addAll(33.7 * k, 24.5 * k,
                        12.0 * k, 37.0 * k,
                        -9.7 * k, 24.5 * k,
                        -9.7 * k, -0.5 * k,
                        12.0 * k, -13.0 * k,
                        33.7 * k, -0.5 * k);
            }
        }
    }

    private int getNextRandom() {
        return ThreadLocalRandom.current().nextInt(0, size.get());
    }

    private void genCard(int total, int x, int y) {
        int size = this.size.get();
        minercard = new int[size][size];
        mines = size * size - total;
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

        mainCol.getScene().widthProperty().addListener((observable, oldValue, newValue) -> resize((double) newValue / (double) oldValue));
        mainCol.getScene().heightProperty().addListener((observable, oldValue, newValue) -> resize((double) newValue / (double) oldValue));
        int x = Integer.valueOf(String.valueOf((((Polygon) event.getSource()).getId().charAt(0))));
        int y = Integer.valueOf(String.valueOf((((Polygon) event.getSource()).getId().charAt(1))));
        Point p = new Point(x, y);
        Polygon pol = ((Polygon) event.getSource());
        if (event.getButton() == MouseButton.SECONDARY) {
            if (minercard == null) return;
            if (points.contains(p)) {
                points.remove(p);
                (((StackPane) pol.getParent())).getChildren().remove(1);
            } else {
                points.add(p);
                Text txt = new Text();
                txt.setText("F");
                ((StackPane) pol.getParent()).getChildren().add(txt);
            }
        } else {
            if (points.contains(p)) return;
            if (minercard == null)
                genCard(size.get() - 1 > 0 ? size.get() - 1 : 1, x, y);
            mines--;
            if (minercard[x][y] == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("You Lose!");
                alert.setHeaderText("");
                alert.setContentText("Sorry, but tou exploded (х_х)");
                alert.showAndWait();
                Platform.exit();
                System.exit(0);
            }
            Text txt = genLabel(minercard[x][y]);
            pol.setFill(Color.gray(0.8));
            pol.setOnMouseClicked(null);
            StackPane st = (StackPane) pol.getParent();
            st.getChildren().add(txt);
            if (mines == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You Win!");
                alert.setHeaderText("");
                alert.setContentText("Congratulation's! You solved it! (^_^)");
                alert.showAndWait();
                Platform.exit();
                System.exit(0);
            }
        }
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
            return this.x < size.get() && this.x > -1 && this.y < size.get() && this.y > -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
