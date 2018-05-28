package Arkanoid;

public class GameThread extends Thread {
    private Arkanoid game;

    GameThread(Arkanoid game) {
        this.game = game;
    }

    public void run() {
        game.isRunning = true;
        game.isPaused = false;
        while (game.isRunning) {
            try {
                if (game.isPaused) {
                    Thread.sleep(1);
                } else {
                    game.screenUpdate();
                    Thread.sleep(15);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
