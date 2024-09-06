package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Piece;

public interface MoveStrategy {
  boolean canMove(int x, int y, Board board, Piece piece);

  default boolean checkDiagonalMove(int x, int y, int xDirection, int yDirection, Board board, int xCord, int yCord) {
    int j = yCord + yDirection;
    for (int i = xCord + xDirection; i != x; i += xDirection) {
      if (board.getXY(i, j) != 0) {
        return false;
      }
      j += yDirection;
    }
    return true;
  }

  default boolean checkVerticalMove(int y, Board board, int xCord, int yCord) {
    if (y < yCord) {
      for (int i = yCord - 1; i > y; i--) {
        if (board.getXY(xCord, i) != 0) {
          return false;
        }
      }
    } else {
      for (int i = yCord + 1; i < y; i++) {
        if (board.getXY(xCord, i) != 0) {
          return false;
        }
      }
    }
    return true;
  }

  default boolean checkHorizontalMove(int x, Board board, int xCord, int yCord) {
    if (x > xCord) {
      for (int i = xCord + 1; i < x; i++) {
        if (board.getXY(i, yCord) != 0) {
          return false;
        }
      }
    } else {
      for (int i = xCord - 1; i > x; i--) {
        if (board.getXY(i, yCord) != 0) {
          return false;
        }
      }
    }
    return true;
  }
}
