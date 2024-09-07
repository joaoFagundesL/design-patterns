package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.strategy.BishopMoveStrategy;

public class Bishop extends Piece {

  public Bishop(int x, int y, boolean iswhite, Board board, int value) {
    super(x, y, iswhite, board, value, new BishopMoveStrategy());
    this.pieceImage = PieceImages.BISHOP;
  }

  public void intializeSide(int value){
    super.intializeSide(value);
    if(isWhite()) {
      image = PieceImages.wb;
    }
    else {
      image = PieceImages.bb;
    }
  }
}
