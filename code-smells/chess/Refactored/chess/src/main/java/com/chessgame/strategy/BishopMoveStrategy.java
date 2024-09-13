package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;

public class BishopMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {

    final Piece targetPiece = board.getPiece(positionX, positionY);
    final boolean isOccupiedByOwnPiece =
        targetPiece != null && targetPiece.isWhite() == piece.isWhite();
    final boolean isDiagonalMove =
        Math.abs(positionX - piece.getXcord()) == Math.abs(positionY - piece.getYcord());

    return !isOccupiedByOwnPiece
        && isDiagonalMove
        && bishopMoves(positionX, positionY, board, piece.getXcord(), piece.getYcord());
  }

  private boolean bishopMoves(
      final int positionX,
      final int positionY,
      final Board board,
      final int xCord,
      final int yCord) {

    boolean movePossible = false;

    if (positionX > xCord && positionY > yCord) {
      movePossible = checkDiagonalMove(positionX, positionY, 1, 1, board, xCord, yCord);
    } else if (positionX < xCord && positionY < yCord) {
      movePossible = checkDiagonalMove(positionX, positionY, -1, -1, board, xCord, yCord);
    } else if (positionX > xCord && positionY < yCord) {
      movePossible = checkDiagonalMove(positionX, positionY, 1, -1, board, xCord, yCord);
    } else if (positionX < xCord && positionY > yCord) {
      movePossible = checkDiagonalMove(positionX, positionY, -1, 1, board, xCord, yCord);
    }

    return movePossible;
  }
}
