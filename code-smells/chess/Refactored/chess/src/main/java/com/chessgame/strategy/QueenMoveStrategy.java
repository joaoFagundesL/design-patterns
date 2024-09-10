package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.Queen;

public class QueenMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {
    final Queen queen = (Queen) piece;
    final int xCord = queen.getXcord();
    final int yCord = queen.getYcord();

    if (board.getPiece(positionX, positionY) != null
        && board.getPiece(positionX, positionY).isWhite() == queen.isWhite()) {
      return false;
    }

    if (Math.abs(positionX - xCord) == Math.abs(positionY - yCord)) {
      return queenMovesDiagonial(positionX, positionY, board, xCord, yCord);
    } else if (positionX == xCord || positionY == yCord) {
      return queenMovesStraight(positionX, positionY, board, xCord, yCord);
    }

    return false;
  }

  public boolean queenMovesStraight(
      final int positionX,
      final int positionY,
      final Board board,
      final int xCord,
      final int yCord) {
    if (positionX == xCord) {
      return checkVerticalMove(positionY, board, xCord, yCord);
    } else if (positionY == yCord) {
      return checkHorizontalMove(positionX, board, xCord, yCord);
    }
    return false;
  }

  public boolean queenMovesDiagonial(
      final int positionX,
      final int positionY,
      final Board board,
      final int xCord,
      final int yCord) {

    if (positionX > xCord && positionY > yCord) {
      return checkDiagonalMove(positionX, positionY, 1, 1, board, xCord, yCord);
    }
    if (positionX < xCord && positionY < yCord) {
      return checkDiagonalMove(positionX, positionY, -1, -1, board, xCord, yCord);
    }
    if (positionX > xCord && positionY < yCord) {
      return checkDiagonalMove(positionX, positionY, 1, -1, board, xCord, yCord);
    }
    if (positionX < xCord && positionY > yCord) {
      return checkDiagonalMove(positionX, positionY, -1, 1, board, xCord, yCord);
    }
    return false;
  }
}
