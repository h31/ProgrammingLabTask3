import units.BaseHero;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    int moveCounter = 0;
    private Game game;

    public MouseManager(Game game) {
        this.game = game;
    }

    public void tick() {
        game.x = game.toGrid(mouseX, mouseY)[0];
        game.y = game.toGrid(mouseX, mouseY)[1];
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void moved(BaseHero player, int x, int y) {
        if (player.getMoves() >= Math.sqrt(Math.pow(game.x - x, 2)
                + Math.pow(game.y - y, 2))) {
            if (moveCounter % 2 == 0) {
                game.playerOneX = game.x;
                game.playerOneY = game.y;
            } else {
                game.playerTwoX = game.x;
                game.playerTwoY = game.y;
            }
            if (game.world[game.x][game.y] == 2) {
                System.out.println(player.getNameHero() + " попал в лаву и получил 5 урона.");
                player.damage(5);
            }
            moveCounter++;
        } else {
            System.out.println("Вы слишком далеко от этого места.");
        }
    }

    public void attack(BaseHero player, BaseHero enemy, int playerX, int playerY, int enemyX, int enemyY) {
        if (player.getRadiusAttack() >= (Math.sqrt(Math.pow(playerX - enemyX, 2)
                + Math.pow(playerY - enemyY, 2)))) {
            player.degradeEnemy(enemy);
            moveCounter++;
        } else {
            System.out.println("Радиус атаки сликшом мал. Подойдите ближе к врагу.");
        }
    }

    public void heal(BaseHero player) {
        if (player.getHeal() > 0) {
            player.heal(player);
            System.out.println(player.getNameHero() + " отлечился.");
            moveCounter++;
        } else {
            System.out.println("No heals.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.x > game.getWorld().length - 1 || game.x < 0 || game.y > game.getWorld().length - 1 || game.y < 0) {
            System.out.println("Вы нажали за пределы игрового поля.");
        } else if (game.world[game.x][game.y] == 1) {
            System.out.println("Нельзя переместиться сюда.");
        } else if (moveCounter % 2 == 0) {
            if (game.x == game.playerOneX && game.y == game.playerOneY) {
                heal(game.playerOne);
            } else if (game.x == game.playerTwoX && game.y == game.playerTwoY) {
                attack(game.playerOne, game.playerTwo, game.playerOneX, game.playerOneY, game.playerTwoX, game.playerTwoY);
            } else {
                moved(game.playerOne, game.playerOneX, game.playerOneY);
            }
        } else {
            if (game.x == game.playerTwoX && game.y == game.playerTwoY) {
                heal(game.playerTwo);
            } else if (game.x == game.playerOneX && game.y == game.playerOneY) {
                attack(game.playerTwo, game.playerOne, game.playerTwoX, game.playerTwoY, game.playerOneX, game.playerOneY);
            } else {
                moved(game.playerTwo, game.playerTwoX, game.playerTwoY);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
