package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.strategy.RookMoveStrategy;

public class Rook extends Piece {

  private boolean hasMoved;
  private boolean justMoved = false;

  public Rook(
      final int horizontalPos,
      final int verticalPos,
      final boolean iswhite,
      final Board board,
      final int value) {
    super(horizontalPos, verticalPos, iswhite, board, value, new RookMoveStrategy());
    hasMoved = false;
    this.pieceImage = PieceImages.ROOK;
  }

  public void intializeSide(final int value) {
    super.intializeSide(value);
    if (isWhite()) {
      image = PieceImages.wr;
    } else {
      image = PieceImages.br;
    }
  }

  @Override
  public boolean makeMove(final int toHorizontal, final int toVertical, final Board board) {
    if (super.makeMove(toHorizontal, toVertical, board)) {
      if (!hasMoved) {
        justMoved = true;
      } else {
        justMoved = false;
      }
      hasMoved = true;
      return true;
    }
    return false;
  }

  public void castleDone(final int horizontalPos, final Board board) {
    if (horizontalPos == 6) {
      board.updatePieces(xCord, yCord, horizontalPos - 1, yCord, this);
      xCord = horizontalPos - 1;
    } else {
      board.updatePieces(xCord, yCord, horizontalPos + 1, yCord, this);
      xCord = horizontalPos + 1;
    }
    hasMoved = true;
  }

  public boolean hasMoved() {
    return hasMoved;
  }

  public void setHasMoved(final boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  public boolean isJustMoved() {
    return justMoved;
  }

  public void setJustMoved(final boolean justMoved) {
    this.justMoved = justMoved;
  }
}
