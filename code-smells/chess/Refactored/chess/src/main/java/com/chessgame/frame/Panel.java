package com.chessgame.frame;

import com.chessgame.game.Game;
import com.chessgame.pieces.Piece;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel {

  private static final long serialVersionUID = 1L;
  Game game;
  int temporaryIndexI, temporaryIndexJ;
  public static int xx, yy;
  JPanel panel = this;

  Panel() {
    this.setFocusable(true);
    this.addMouseListener(new Listener());
    this.addMouseMotionListener(new Listener());
    this.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 37) {
              Game.board.undoMove();
            }
          }
        });
    game = new Game();
  }

  @Override
  public void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);
    game.draw(graphics, xx, yy, this);
  }

  class Listener extends MouseAdapter {
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
      if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
        final int positionX = mouseEvent.getX() / Piece.size;
        final int positionY = mouseEvent.getY() / Piece.size;
        Game.drag = false;
        game.active = null;
        game.selectPiece(positionX, positionY);
        revalidate();
        repaint();
      }
    }

    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
      // temp index i and j for the gui
      temporaryIndexI = mouseEvent.getX() / Piece.size;
      temporaryIndexJ = mouseEvent.getY() / Piece.size;
      if (Game.board.getPiece(temporaryIndexI, temporaryIndexJ) != null) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
      } else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
      revalidate();
      repaint();
    }

    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
      if (!Game.drag && game.active != null) {
        game.active = null;
      }
      if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
        game.selectPiece(mouseEvent.getX() / Piece.size, mouseEvent.getY() / Piece.size);
        Game.drag = true;
        xx = mouseEvent.getX();
        yy = mouseEvent.getY();
      }
      revalidate();
      repaint();
    }

    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
      final int positionX = mouseEvent.getX() / Piece.size;
      final int positionY = mouseEvent.getY() / Piece.size;
      game.move(positionX, positionY);
      revalidate();
      repaint();
    }
  }
}
