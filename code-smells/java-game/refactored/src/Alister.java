package src;

import java.awt.Color;
import java.awt.Graphics;

public class Alister extends Item {

  private static final int SIZE = 30;
  private static final int BORDER_LIMIT = 570;

  public Alister(final int startX, final int startY, final int totalSize) {
    super(startX, startY, totalSize);
  }

  public void draw(final Graphics graphics) {
    final int horizonal = super.getHorizontal();
    final int vertical = super.getVertical();
    graphics.setColor(Color.YELLOW);

    final int[] xpts =
        new int[] {
          horizonal + 8,
          horizonal + 16,
          horizonal + 16,
          horizonal + 24,
          horizonal + 24,
          horizonal + 16,
          horizonal + 16,
          horizonal + 24,
          horizonal + 24,
          horizonal + 24,
          horizonal + 16,
          horizonal + 16,
          horizonal + 8,
          horizonal,
          horizonal,
          horizonal,
          horizonal + 8,
          horizonal + 8,
          horizonal,
          horizonal,
          horizonal + 8
        };

    final int[] ypts =
        new int[] {
          vertical,
          vertical,
          vertical + 6,
          vertical + 6,
          vertical + 12,
          vertical + 12,
          vertical + 18,
          vertical + 18,
          vertical + 24,
          vertical + 30,
          vertical + 30,
          vertical + 24,
          vertical + 24,
          vertical + 30,
          vertical + 24,
          vertical + 18,
          vertical + 18,
          vertical + 12,
          vertical + 12,
          vertical + 6,
          vertical + 6
        };

    graphics.fillPolygon(xpts, ypts, xpts.length);
  }

  public boolean collideBorder() {
    final int horizontal = super.getHorizontal();
    final int vertical = super.getVertical();
    return horizontal <= 0
        || horizontal >= BORDER_LIMIT
        || vertical <= 0
        || vertical >= BORDER_LIMIT;
  }

  public boolean collideWithMonster(final Monster monster) {
    final int positionX = super.getHorizontal();
    final int positionY = super.getVertical();
    final int monsterX = monster.getHorizontal();
    final int monsterY = monster.getVertical();
    return (monsterX >= (positionX - SIZE) && monsterX <= (positionX + SIZE))
        && (monsterY >= (positionY - SIZE) && monsterY <= (positionY + SIZE));
  }
}
