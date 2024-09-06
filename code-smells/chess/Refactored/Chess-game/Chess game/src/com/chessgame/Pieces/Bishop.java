package com.chessgame.Pieces;

import com.chessgame.Board.*;
import com.chessgame.Strategy.BishopMoveStrategy;
import com.chessgame.Strategy.MoveStrategy;

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
