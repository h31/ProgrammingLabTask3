import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Controller {
    static int width;
    static int height;
    static Minesweeper game;
    static Pane area;
    static Polygon[][] cells;
    static ImagePattern num1 = new ImagePattern(new Image("num1.png"));
    static ImagePattern num2 = new ImagePattern(new Image("num2.png"));
    static ImagePattern num3 = new ImagePattern(new Image("num3.png"));
    static ImagePattern num4 = new ImagePattern(new Image("num4.png"));
    static ImagePattern num5 = new ImagePattern(new Image("num5.png"));
    static ImagePattern num6 = new ImagePattern(new Image("num6.png"));

    public static void init(int width, int height) {

        Controller.width = width;
        Controller.height = height;
        area.getChildren().clear();
        cells = new Polygon[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                final int column = j;
                final int row = i;
                if (i % 2 == 0) {
                    cells[i][j] = drawGex(20 + 60 * j, 17 + 17 * i, 20);
                    cells[i][j].setStroke(Color.ORANGE);
                    cells[i][j].setFill(Color.BLUE);
                    area.getChildren().add(cells[i][j]);


                } else {
                    cells[i][j] = drawGex(50 + 60 * j, 17 + 17 * i, 20);
                    cells[i][j].setStroke(Color.ORANGE);
                    cells[i][j].setFill(Color.BLUE);
                    area.getChildren().add(cells[i][j]);
                }
                cells[i][j].setOnMouseClicked(event -> {
                    switch (event.getButton()) {
                        case PRIMARY:
                            game.clickCell(column, row);
                            break;
                        case SECONDARY:
                            game.toggleFlag(column, row);
                            break;
                    }
                });
            }
        }
    }

    public static void refresh() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = game.getField().getCells()[i][j];
                if (cell.flag()) {
                    cells[i][j].setFill(Color.YELLOW);
                } else {
                    cells[i][j].setFill(Color.BLUE);
                }
                if (cell.getVisibility()) {
                    cells[i][j].setFill(Color.LIGHTGRAY);
                    if (cell.getMine()) {
                        cells[i][j].setFill(Color.RED);
                    } else if (cell.getNearestMinesCount() != 0) {
                        switch (cell.getNearestMinesCount()) {
                            case 1:
                                cells[i][j].setFill(num1);
                                break;
                            case 2:
                                cells[i][j].setFill(num2);
                                break;
                            case 3:
                                cells[i][j].setFill(num3);
                                break;
                            case 4:
                                cells[i][j].setFill(num4);
                                break;
                            case 5:
                                cells[i][j].setFill(num5);
                                break;
                            case 6:
                                cells[i][j].setFill(num6);
                                break;
                        }
                    }
                }
            }
        }
        if (game.gameOver()) {
            Text text = new Text(area.getWidth() / 2 - 90, area.getHeight() / 2, "GAME OVER!");
            text.setFont(new Font(30));
            text.setTextAlignment(TextAlignment.CENTER);
            area.getChildren().add(text);
        }
        if (game.win()) {
            Text text = new Text(area.getWidth() / 2 - 70, area.getHeight() / 2, "YOU WIN!");
            text.setFont(new Font(30));
            text.setTextAlignment(TextAlignment.CENTER);
            area.getChildren().add(text);
        }
    }

    private static Polygon drawGex(int x, int y, int r) {
        return new Polygon(
                x - r, y,
                x - r / 2, y - r * 0.85,
                x + r / 2, y - r * 0.85,
                x + r, y,
                x + r / 2, y + r * 0.85,
                x - r / 2, y + r * 0.85
        );
    }
}
