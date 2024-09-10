package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.strategy.BishopMoveStrategy;

public class Bishop extends Piece {

  public Bishop(
      final int positionX,
      final int positionY,
      final boolean iswhite,
      final Board board,
      final int value) {
    super(positionX, positionY, iswhite, board, value, new BishopMoveStrategy());
    this.pieceImage = PieceImages.BISHOP;
  }

  public void intializeSide(final int value) {
    super.intializeSide(value);
    if (isWhite()) {
      image = PieceImages.wb;
    } else {
      image = PieceImages.bb;
    }
  }
}
