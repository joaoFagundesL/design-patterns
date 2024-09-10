package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Knight;
import com.chessgame.pieces.Piece;

public class KnightMoveStrategy implements MoveStrategy {
  @Override
  public boolean canMove(final int x, final int y, final Board board, final Piece piece) {
    final Knight knight = (Knight) piece;
    final int xCord = knight.getXcord();
    final int yCord = knight.getYcord();

    if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == knight.isWhite())) {
      return false;
    }

    return (x == xCord + 1 && y == yCord - 2)
        || (x == xCord - 1 && y == yCord - 2)
        || (x == xCord - 1 && y == yCord + 2)
        || (x == xCord + 1 && y == yCord + 2)
        || (x == xCord + 2 && y == yCord - 1)
        || (x == xCord + 2 && y == yCord + 1)
        || (x == xCord - 2 && y == yCord - 1)
        || (x == xCord - 2 && y == yCord + 1);
  }
}
