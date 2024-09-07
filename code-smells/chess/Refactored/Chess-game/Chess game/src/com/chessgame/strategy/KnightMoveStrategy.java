package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Knight;
import com.chessgame.pieces.Piece;

public class KnightMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int x, int y, Board board, Piece piece) {
    Knight knight = (Knight) piece;
    int xCord = knight.getXcord();
    int yCord = knight.getYcord();

    if((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == knight.isWhite())) {
      return false;
    }

    if(x == xCord+1 && y == yCord-2 ) {
      return true;
    }
    if(x == xCord-1 && y == yCord-2 ) {
      return true;	
    }
    if(x == xCord-1 && y == yCord+2 ) {
      return true;	
    }
    if(x == xCord+1 && y == yCord+2 ) {
      return true;	
    }
    if(x == xCord+2 && y == yCord-1 ) {
      return true;	
    }
    if(x == xCord+2 && y == yCord+1 ) {
      return true;	
    }
    if(x == xCord-2 && y == yCord-1 ) {	
      return true;	
    }
    if(x == xCord-2 && y == yCord+1 ) {
      return true;	
    }

    return false;
  }

}
