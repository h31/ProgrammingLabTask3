import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.MouseInfoPeer;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    int moveCounter = 0;
    private Game game;
    public MouseManager(Game game) {
        this.game = game;
    }

    public void tick(){
        game.x = game.toGrid(mouseX,mouseY)[0];
        game.y = game.toGrid(mouseX,mouseY)[1];
    }

    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        } else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if(e.getButton() == MouseEvent.BUTTON3){
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.x > game.getWorld().length - 1 || game.x < 0 || game.y > game.getWorld().length - 1 || game.y < 0) {
            System.out.println("Вы нажали за пределы игрового поля.");
        } else if (game.world[game.x][game.y] == 1) {
            System.out.println("Нельзя переместиться сюда.");
        } else if (moveCounter % 2 == 0) {
            if (game.x == game.knightX && game.y == game.knightY) {
                game.knight.heal(game.knight);
                System.out.println("Knight отлечился.");
                moveCounter++;
            } else if (game.x == game.sectoidX && game.y == game.sectoidY) {
                if (game.knight.getRadiusAttack() >= (Math.sqrt(Math.abs(game.knightX - game.sectoidX)^2
                        + Math.abs(game.knightY - game.sectoidY)^2))){
                    game.knight.degrade(game.sectoid);
                    moveCounter++;
                } else {
                    System.out.println("Радиус атаки сликшом мал. Подойдите ближе к врагу.");
                }
            } else {
                if (game.knight.getMoves() >= Math.sqrt(Math.pow(game.x - game.knightX,2)
                        + Math.pow(game.y - game.knightY,2))) {
                    game.knightX = game.x;
                    game.knightY = game.y;
                    if (game.world[game.x][game.y] == 2){
                        System.out.println("Knight попал в лаву и получил 5 урона.");
                        game.knight.damage(5);
                    }
                    moveCounter++;
                } else {
                    System.out.println("Вы слишком далеко от этого места.");
                }
            }
        } else {
            if (game.x == game.sectoidX && game.y == game.sectoidY) {
                game.sectoid.heal(game.sectoid);
                System.out.println("Sectoid отлечился.");
                moveCounter++;
            } else if (game.x == game.knightX && game.y == game.knightY) {
                if (game.sectoid.getRadiusAttack() >= (Math.sqrt(Math.abs(game.knightX - game.sectoidX)^2
                        + Math.abs(game.knightY - game.sectoidY)^2))){
                    game.sectoid.degrade(game.knight);
                    moveCounter++;
                } else {
                    System.out.println("Радиус атаки сликшом мал. Подойдите ближе к врагу.");
                }
            }
            else {
                if (game.sectoid.getMoves() >= Math.sqrt(Math.pow(game.x - game.sectoidX,2)
                        + Math.pow(game.y - game.sectoidY,2))) {
                    game.sectoidX = game.x;
                    game.sectoidY = game.y;
                    if (game.world[game.x][game.y] == 2){
                        System.out.println("Sectoid попал в лаву и получил 5 урона.");
                        game.sectoid.damage(5);
                    }
                    moveCounter++;
                } else {
                    System.out.println("Вы слишком далеко от этого места.");
                }
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
