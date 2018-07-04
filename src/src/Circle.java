import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Circle extends JPanel {
    private int x;
    private int y;
    private int diameter;
    private Color color;
    public Circle(int x, int y, int diameter, Color color){
        super();
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDiameter(){
        return diameter;
    }
    public void paintComponent(Graphics g){
       g.setColor(Color.BLACK);
       repaint();
    }
}
