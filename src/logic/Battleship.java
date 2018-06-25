package logic;

import logic.Cell;
import UI.Main;
import UI.Screen;
import logic.Ship;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Battleship {
    private Screen userScreen;
    private Screen computerScreen;
    private static Stage USER_STAGE;
    private static Stage COMPUTER_STAGE;
    private List<Cell> damageCellsOfUser = new ArrayList<>();

    private Battleship(Screen userScreen, Screen computerScreen) {
        this.userScreen = userScreen;
        this.computerScreen = computerScreen;
        userScreen.blockingScreen(true);
        computerScreen.blockingScreen(false);
    }

    public void handlerHint(Cell cell) {
        if (cell.isDamage()) return;
        if (computerScreen.getCells().contains(cell) && cell.isDisable()) return;
        if (userScreen.getCells().contains(cell)) {
            boolean isShip = cell.getId().equals("user_ship");
            computerScreen.blockingScreen(isShip);
            String classAndId = isShip ? "injured_ship" : "miss";
            cell.getStyleClass().add(classAndId);
            cell.setId(classAndId);
            cell.setDamage(true);
            if (isShip) {
                checkWin();
                computerHint();
            }
        } else {
            boolean isShip = cell.getId().equals("hidden_ship");
            String classAndId = isShip ? "injured_ship" : "miss";
            cell.getStyleClass().add(classAndId);
            cell.setId(classAndId);
            cell.setDisable(false);
            cell.setDamage(true);
            if (isShip) {
                checkWin();
            } else {
                computerScreen.blockingScreen(true);
                computerHint();
            }
        }
    }

    private void computerHint() {
        Random random = new Random();
        int cellIndex = random.nextInt(userScreen.getCells().size());
        Cell cellforHint = userScreen.getCells().get(cellIndex);
        if (damageCellsOfUser.contains(cellforHint)) {
            computerHint();
        }
        else {
            damageCellsOfUser.add(cellforHint);
            handlerHint(cellforHint);
        }
    }

    private boolean isWin(boolean isUser) {
        for (Cell cell : isUser ? computerScreen.getCells() : userScreen.getCells()) {
            if (cell.getId().equals(isUser ? "hidden_ship" : "user_ship")) {
                return false;
            }
        }
        return true;
    }

    private void checkWin() {
        boolean winUser = isWin(true);
        boolean winComputer = isWin(false);
        if (winUser ^ winComputer){
            String winner = winUser ? "You" : "Computer";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(winner + " win!");
            alert.setContentText("Start new game?");
            Optional<ButtonType> buttonOfAlert = alert.showAndWait();
            if (buttonOfAlert.get() == ButtonType.OK) {
                createNewGame();
            }
            else {
                USER_STAGE.close();
                COMPUTER_STAGE.close();
            }
        }
    }

    public static void createNewGame() {
        if ((USER_STAGE != null) && (USER_STAGE.isShowing())) {
            USER_STAGE.close();
        }
        if ((COMPUTER_STAGE != null) && COMPUTER_STAGE.isShowing()) {
            COMPUTER_STAGE.close();
        }
        Battleship battleship = new Battleship(createField(true), createField(false));
        Cell.setBattleship(battleship);
    }

    private static Screen createField(boolean isUser) {
        int sceneSize = isUser ? 300 : 600;
        int shift = isUser ? -250 : 250;
        int shipType = isUser ? 1 : 4;
        String whoseIsShip = isUser ? "Our" : "Computer";
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, sceneSize, sceneSize);
        scene.getStylesheets().add(Main.class.getResource("/StyleForBattleship.css").toExternalForm());
        List<Ship> ships = Ship.randomGenerateShips();
        Screen screen = new Screen(gridPane);
        screen.shipScreen(ships, shipType);
        Stage stage = new Stage();
        stage.setTitle(whoseIsShip + " ships!");
        stage.setScene(scene);
        stage.setResizable(false);
        if (isUser) {
            USER_STAGE = stage;
            USER_STAGE.show();
        }
        else {
            COMPUTER_STAGE = stage;
            COMPUTER_STAGE.show();
        }
        stage.setX(stage.getX() + shift);
        return screen;
    }
}
