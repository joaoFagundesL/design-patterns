package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Knight;
import com.chessgame.pieces.Piece;

public class KnightMoveStrategy implements MoveStrategy {
  @Override
  public boolean canMove(
      final int horizontalPos, final int verticalPos, final Board board, final Piece piece) {
    final Knight knight = (Knight) piece;
    final int xCord = knight.getXcord();
    final int yCord = knight.getYcord();

    if ((board.getPiece(horizontalPos, verticalPos) != null
        && board.getPiece(horizontalPos, verticalPos).isWhite() == knight.isWhite())) {
      return false;
    }

    return (horizontalPos == xCord + 1 && verticalPos == yCord - 2)
        || (horizontalPos == xCord - 1 && verticalPos == yCord - 2)
        || (horizontalPos == xCord - 1 && verticalPos == yCord + 2)
        || (horizontalPos == xCord + 1 && verticalPos == yCord + 2)
        || (horizontalPos == xCord + 2 && verticalPos == yCord - 1)
        || (horizontalPos == xCord + 2 && verticalPos == yCord + 1)
        || (horizontalPos == xCord - 2 && verticalPos == yCord - 1)
        || (horizontalPos == xCord - 2 && verticalPos == yCord + 1);
  }
}
