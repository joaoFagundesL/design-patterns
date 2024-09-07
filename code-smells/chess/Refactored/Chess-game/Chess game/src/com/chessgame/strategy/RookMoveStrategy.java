package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.Rook;

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
