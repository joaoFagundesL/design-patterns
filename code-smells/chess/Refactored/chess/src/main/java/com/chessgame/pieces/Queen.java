package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.strategy.QueenMoveStrategy;

public class Queen extends Piece {

  public Queen(
      final int horizontalPos,
      final int verticalPos,
      final boolean iswhite,
      final Board board,
      final int value) {
    super(horizontalPos, verticalPos, iswhite, board, value, new QueenMoveStrategy());
    this.pieceImage = PieceImages.QUEEN;
  }

  public void intializeSide(final int value) {
    super.intializeSide(value);
    if (isWhite()) {
      image = PieceImages.wq;
    } else {
      image = PieceImages.bq;
    }
  }
}
