package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.Rook;

public class RookMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {
    final Rook rook = (Rook) piece;
    final int xCord = rook.getXcord();
    final int yCord = rook.getYcord();

    if (board.getPiece(positionX, positionY) != null
        && board.getPiece(positionX, positionY).isWhite() == rook.isWhite()) {
      return false;
    }

    if (positionX == xCord) {
      return checkVerticalMove(positionY, board, xCord, yCord);
    } else if (positionY == yCord) {
      return checkHorizontalMove(positionX, board, xCord, yCord);
    }

    return false;
  }
}
