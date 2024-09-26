package panel;

import java.awt.Graphics;

public abstract class BaseRenderer {
  protected final GamePanel panel;

  public BaseRenderer(final GamePanel panel) {
    this.panel = panel;
  }

  public final void draw(final Graphics graphics) {
    if (panel.isRunning()) {
      drawGrid(graphics);
      drawApple(graphics);
      drawSnake(graphics);
    } else {
      gameOver(graphics);
    }
  }

  public abstract void drawGrid(Graphics graphics);

  public abstract void drawApple(Graphics graphics);

  public abstract void drawSnake(Graphics graphics);

  public abstract void gameOver(Graphics graphics);
}
