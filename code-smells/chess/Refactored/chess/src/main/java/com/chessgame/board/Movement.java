package com.chessgame.board;

import com.chessgame.pieces.Piece;

public class Movement implements Comparable<Movement> {
  int fromX, fromY, toX, toY;
  Piece piece;

  public Movement(
      final int fromX, final int fromY, final int toX, final int toY, final Piece piece) {
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
    this.piece = piece;
  }

  public int getFromX() {
    return fromX;
  }

  public void setFromX(final int fromX) {
    this.fromX = fromX;
  }

  public int getFromY() {
    return fromY;
  }

  public void setFromY(final int fromY) {
    this.fromY = fromY;
  }

  public int getToX() {
    return toX;
  }

  public void setToX(final int toX) {
    this.toX = toX;
  }

  public int getToY() {
    return toY;
  }

  public void setToY(final int toY) {
    this.toY = toY;
  }

  public Piece getPiece() {
    return piece;
  }

  public void setPiece(final Piece piece) {
    this.piece = piece;
  }

  @Override
  public int compareTo(final Movement movement) {
    return (toX == movement.getToX() && toY == movement.getToY()) ? 0 : -1;
  }

  @Override
  public boolean equals(final Object object) {
    final Movement otherM = (Movement) object;

    return this.getToX() == otherM.getToX()
        && this.getToY() == otherM.getToY()
        && this.getFromX() == otherM.getFromX()
        && this.getFromY() == otherM.getFromY();
  }
}
