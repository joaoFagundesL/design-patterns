package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Piece;
import com.chessgame.Pieces.Rook;

public class RookMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int x, int y, Board board, Piece piece) {
    Rook rook = (Rook) piece;
    int xCord = rook.getXcord();
    int yCord = rook.getYcord();

    if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == rook.isWhite()) {
      return false;
    }

    if (x == xCord) {
      return checkVerticalMove(y, board, xCord, yCord);
    } else if (y == yCord) {
      return checkHorizontalMove(x, board, xCord, yCord);
    }

    return false;
  }
}
