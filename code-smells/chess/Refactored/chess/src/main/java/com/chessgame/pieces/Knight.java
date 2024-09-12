package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.strategy.KnightMoveStrategy;

public class Knight extends Piece {

  public Knight(
      final int horizontalPos,
      final int verticalPos,
      final boolean iswhite,
      final Board board,
      final int value) {
    super(horizontalPos, verticalPos, iswhite, board, value, new KnightMoveStrategy());
    this.pieceImage = PieceImages.KNIGHT;
  }

  public void intializeSide(final int value) {
    super.intializeSide(value);
    if (isWhite()) {
      image = PieceImages.wn;
    } else {
      image = PieceImages.bn;
    }
  }
}
