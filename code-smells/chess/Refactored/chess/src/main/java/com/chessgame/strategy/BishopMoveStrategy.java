package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;

public class BishopMoveStrategy implements MoveStrategy {
  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {

    return (board.getPiece(positionX, positionY) != null
            && board.getPiece(positionX, positionY).isWhite() == piece.isWhite())
        ? false
        : (Math.abs(positionX - piece.getXcord()) == Math.abs(positionY - piece.getYcord()))
            ? bishopMoves(positionX, positionY, board, piece.getXcord(), piece.getYcord())
            : false;
  }

  private boolean bishopMoves(
      final int positionX,
      final int positionY,
      final Board board,
      final int xCord,
      final int yCord) {

    return (positionX > xCord && positionY > yCord)
        ? checkDiagonalMove(positionX, positionY, 1, 1, board, xCord, yCord)
        : (positionX < xCord && positionY < yCord)
            ? checkDiagonalMove(positionX, positionY, -1, -1, board, xCord, yCord)
            : (positionX > xCord && positionY < yCord)
                ? checkDiagonalMove(positionX, positionY, 1, -1, board, xCord, yCord)
                : (positionX < xCord && positionY > yCord)
                    ? checkDiagonalMove(positionX, positionY, -1, 1, board, xCord, yCord)
                    : false;
  }
}
