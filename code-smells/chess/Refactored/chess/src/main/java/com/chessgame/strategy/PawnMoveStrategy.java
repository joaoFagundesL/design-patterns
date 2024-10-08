package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;

public class PawnMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {

    final Pawn pawn = (Pawn) piece;
    final int xCord = pawn.getXcord();
    final int yCord = pawn.getYcord();

    return !isBlocked(positionX, positionY, board, pawn)
        && !cantMoveDiagonal(xCord, positionX, positionY, board)
        && (canEnPassantLeft(xCord, yCord, positionX, positionY, board, pawn)
            || canEnPassantRight(xCord, yCord, positionX, positionY, board, pawn)
            || canMoveForward(positionX, positionY, xCord, yCord, board, pawn)
            || pawn.capture(positionX, positionY, board));
  }

  private boolean isBlocked(
      final int positionX, final int positionY, final Board board, final Pawn pawn) {
    final Piece targetPiece = board.getPiece(positionX, positionY);
    return targetPiece != null && targetPiece.isWhite() == pawn.isWhite();
  }

  private boolean cantMoveDiagonal(
      final int xCord, final int positionX, final int positionY, final Board board) {
    return xCord != positionX && board.getPiece(positionX, positionY) == null;
  }

  private boolean canEnPassantLeft(
      final int xCord,
      final int yCord,
      final int positionX,
      final int positionY,
      final Board board,
      final Pawn pawn) {
    final int enpassant = pawn.isWhite() ? -1 : 1;
    return xCord > 0
        && xCord < 7
        && board.getXY(xCord + 1, yCord) == enpassant
        && positionX == ((Pawn) board.getPiece(xCord + 1, yCord)).getXcord()
        && positionY == ((Pawn) board.getPiece(xCord + 1, yCord)).getYcord() + enpassant
        && ((Pawn) board.getPiece(xCord + 1, yCord)).isMoved2Squares();
  }

  private boolean canEnPassantRight(
      final int xCord,
      final int yCord,
      final int positionX,
      final int positionY,
      final Board board,
      final Pawn pawn) {
    final int enpassant = pawn.isWhite() ? -1 : 1;
    return xCord > 0
        && xCord < 7
        && board.getXY(xCord - 1, yCord) == enpassant
        && positionX == ((Pawn) board.getPiece(xCord - 1, yCord)).getXcord()
        && positionY == ((Pawn) board.getPiece(xCord - 1, yCord)).getYcord() + enpassant
        && ((Pawn) board.getPiece(xCord - 1, yCord)).isMoved2Squares();
  }

  private boolean canMoveForward(
      final int positionX,
      final int positionY,
      final int xCord,
      final int yCord,
      final Board board,
      final Pawn pawn) {
    final int forwardDirection = pawn.isWhite() ? -1 : 1;
    final int firstMoveDistance = pawn.isWhite() ? -2 : 2;
    final int nextRow = yCord + forwardDirection;

    return positionX == xCord
        && ((pawn.isFirstMove()
                && (positionY == yCord + forwardDirection || positionY == yCord + firstMoveDistance)
                && board.getPiece(positionX, positionY) == null
                && board.getPiece(positionX, nextRow) == null)
            || (positionY == nextRow && board.getPiece(positionX, positionY) == null));
  }
}
