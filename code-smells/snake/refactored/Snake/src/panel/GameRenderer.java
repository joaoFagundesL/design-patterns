package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameRenderer extends BaseRenderer {

  public GameRenderer(final GamePanel panel) {
    super(panel);
  }

  @Override
  public void drawGrid(final Graphics graphics) {
    for (int i = 0; i < GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE; i++) {
      graphics.drawLine(
          i * GamePanel.UNIT_SIZE, 0, i * GamePanel.UNIT_SIZE, GamePanel.SCREEN_HEIGHT);
      graphics.drawLine(
          0, i * GamePanel.UNIT_SIZE, GamePanel.SCREEN_WIDTH, i * GamePanel.UNIT_SIZE);
    }
  }

  @Override
  public void drawApple(final Graphics graphics) {
    graphics.setColor(Color.RED);
    graphics.fillOval(
        panel.getAppleX(), panel.getAppleY(), GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
  }

  @Override
  public void drawSnake(final Graphics graphics) {
    for (int i = 0; i < panel.getBodyParts(); i++) {
      if (i == 0) {
        graphics.setColor(Color.GREEN); // Head of the snake
      } else {
        graphics.setColor(GamePanel.SNAKE_BODY_COLOR); // Body of the snake
      }
      graphics.fillRect(
          panel.getHorizontal(i), panel.getVertical(i), GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
    }
  }

  @Override
  public void gameOver(final Graphics graphics) {
    graphics.setColor(Color.RED);
    drawStringWithFont(graphics, "Game Over", new Font("Times New Roman", Font.BOLD, 75), 150, 300);
    drawStringWithFont(
        graphics,
        "Score: " + panel.getApplesEaten(),
        new Font("Times New Roman", Font.BOLD, 50),
        150,
        400);
    drawStringWithFont(
        graphics, "Press Space to Restart", new Font("Times New Roman", Font.BOLD, 25), 150, 500);
    drawStringWithFont(
        graphics, "Press Esc to Exit", new Font("Times New Roman", Font.BOLD, 25), 150, 550);
  }

  private void drawStringWithFont(
      final Graphics graphics,
      final String text,
      final Font font,
      final int horizontal,
      final int vertical) {
    graphics.setFont(font);
    graphics.drawString(text, horizontal, vertical);
  }
}
