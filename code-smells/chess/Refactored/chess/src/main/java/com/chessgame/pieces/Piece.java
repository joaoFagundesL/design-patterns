package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.game.Game;
import com.chessgame.strategy.MoveStrategy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Piece implements Cloneable {
  protected int xCord;
  protected int yCord;
  protected boolean isWhite;
  protected boolean isAlive;
  protected int valueInTheBoard;
  protected Board board;
  protected String pieceImage;
  protected Color pieceColor;
  public static int size = 80;
  protected List<Movement> moves = new ArrayList<>();
  protected ImageIcon image;
  protected MoveStrategy moveStrategy;

  public boolean makeMove(final int toX, final int toY, final Board board) {
    final Movement move = new Movement(xCord, yCord, toX, toY, this);
    if (!alive()) {
      return false;
    }
    for (final Movement movement : moves) {
      if (movement.compareTo(move) == 0) {
        board.updatePieces(xCord, yCord, toX, toY, this);
        xCord = toX;
        yCord = toY;
        return true;
      }
    }
    return false;
  }

  public boolean canMove(final int horizontalPos, final int verticalPos, final Board board) {
    return moveStrategy.canMove(horizontalPos, verticalPos, board, this);
  }

  public boolean alive() {
    if (board.getXY(xCord, yCord) != valueInTheBoard
        || board.getXY(xCord, yCord) == 0
        || board.getPiece(xCord, yCord) == null) {
      isAlive = false;
      Game.AllPieces.remove(getClass());
    }
    return isAlive;
  }

  public void intializeSide(final int value) {
    if (isWhite) {
      pieceColor = PieceImages.WHITECOLOR;
    } else {
      pieceColor = PieceImages.BLACKCOLOR;
    }
    valueInTheBoard = value;
  }

  public Piece(
      final int horizontalPos,
      final int verticalPos,
      final boolean iswhite,
      final Board board,
      final int value,
      final MoveStrategy moveStrategy) {
    this.xCord = horizontalPos;
    this.yCord = verticalPos;
    this.isWhite = iswhite;
    isAlive = true;
    this.board = board;
    intializeSide(value);
    board.setPieceIntoBoard(horizontalPos, verticalPos, this);
    this.moveStrategy = moveStrategy;
  }

  public void showMoves(final Graphics graphics, final JPanel panel) {

    final Graphics2D graphics2D = (Graphics2D) graphics;

    for (final Movement m : moves) {
      if (board.getPiece(m.getToX(), m.getToY()) != null
          && board.getPiece(m.getToX(), m.getToY()).isWhite() != isWhite()) {
        graphics.setColor(Color.ORANGE);
      } else {
        graphics.setColor(Color.DARK_GRAY);
      }
      graphics.fillOval(
          (m.getToX() * size) + size / 3, (m.getToY() * size) + size / 3, size / 3, size / 3);
      graphics2D.setColor(Color.DARK_GRAY);
      if (Game.drag) {
        graphics2D.fillRect(m.getFromX() * size, m.getFromY() * size, size, size);
      } else {
        graphics2D.drawRect(m.getFromX() * size, m.getFromY() * size, size, size);
      }
    }
    panel.revalidate();
    panel.repaint();
  }

  public void draw(final Graphics graphics, final boolean drag, final JPanel panel) {
    graphics.drawImage(
        image.getImage(), xCord * Piece.size, yCord * Piece.size, Piece.size, Piece.size, panel);
    panel.revalidate();
    panel.repaint();
  }

  public void draw2(
      final Graphics graphics,
      final boolean player,
      final int horizontalPos,
      final int verticalPos,
      final JPanel panel) {
    graphics.drawImage(
        image.getImage(),
        horizontalPos - Piece.size / 2,
        verticalPos - Piece.size / 2,
        Piece.size,
        Piece.size,
        panel);
    panel.revalidate();
    panel.repaint();
  }

  public void fillAllPseudoLegalMoves(final Board board) {
    moves = new ArrayList<Movement>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (canMove(i, j, board)) {
          moves.add(new Movement(xCord, yCord, i, j, this));
        }
      }
    }
  }

  public int getXcord() {
    return xCord;
  }

  public void setXcord(final int xcord) {
    this.xCord = xcord;
  }

  public int getYcord() {
    return yCord;
  }

  public void setYcord(final int ycord) {
    this.yCord = ycord;
  }

  public boolean isWhite() {
    return isWhite;
  }

  public void setWhite(final boolean isWhite) {
    this.isWhite = isWhite;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(final Board board) {
    this.board = board;
  }

  public void setValueInTheboard(final int value) {
    this.valueInTheBoard = value;
  }

  public int getValueInTheboard() {
    return valueInTheBoard;
  }

  public List<Movement> getMoves() {
    return moves;
  }

  public void setMoves(final List<Movement> moves) {
    this.moves = moves;
  }

  public Piece getClone() {
    try {
      return (Piece) this.clone();
    } catch (final CloneNotSupportedException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
