import javafx.stage.Stage;

public class Battleship {
    private Screen userScreen;
    private Screen computerScreen;
    private static Stage USER_STAGE;
    private static Stage COMPUTER_STAGE;

    private Battleship(Screen userScreen, Screen computerScreen) {
        this.userScreen = userScreen;
        this.computerScreen = computerScreen;
        userScreen.blockingScreen(true);
        computerScreen.blockingScreen(false);
    }
}
