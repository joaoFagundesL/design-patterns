package src;
import java.awt.Color;
import java.awt.Graphics;

public class Alister extends Item {

    private static final int SIZE = 30;
    private static final int BORDER_LIMIT = 570;

    public Alister(int sx, int sy, int s) {
        super(sx, sy, s);
    }

    public void draw(Graphics g) {
        int x = super.getX();
        int y = super.getY();
        g.setColor(Color.YELLOW);

        int[] xpts = new int[] { 
            x + 8,  x + 16, x + 16, x + 24, x + 24, 
            x + 16, x + 16, x + 24, x + 24, x + 24, 
            x + 16, x + 16, x + 8,  x,     x, 
            x,      x + 8,  x + 8,  x,     x, 
            x + 8 
        };

        int[] ypts = new int[] { 
            y,      y,     y + 6,  y + 6,  y + 12, 
            y + 12, y + 18, y + 18, y + 24, y + 30, 
            y + 30, y + 24, y + 24, y + 30, y + 24, 
            y + 18, y + 18, y + 12, y + 12, y + 6,  
            y + 6 
        };

        g.fillPolygon(xpts, ypts, xpts.length);
    }

    public boolean collideBorder() {
        int x = super.getX();
        int y = super.getY();
        return x <= 0 || x >= BORDER_LIMIT || y <= 0 || y >= BORDER_LIMIT;
    }

    public boolean collideWithMonster(Monster v) {
        int px = super.getX();
        int py = super.getY();
        int vx = v.getX();
        int vy = v.getY();
        return (vx >= (px - SIZE) && vx <= (px + SIZE)) && (vy >= (py - SIZE) && vy <= (py + SIZE));
    }
}
