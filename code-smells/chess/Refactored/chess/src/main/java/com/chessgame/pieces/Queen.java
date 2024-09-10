package com.chessgame.pieces;
import com.chessgame.board.Board;
import com.chessgame.strategy.QueenMoveStrategy;

public class Queen extends Piece {

  public Queen(int x, int y, boolean iswhite, Board board, int value) {
    super(x, y, iswhite, board, value, new QueenMoveStrategy());
    this.pieceImage = PieceImages.QUEEN;
  }

  public void intializeSide(int value){
    super.intializeSide(value);
    if(isWhite()) {
      image = PieceImages.wq;
    }
    else {
      image = PieceImages.bq;
    }
  }


}
