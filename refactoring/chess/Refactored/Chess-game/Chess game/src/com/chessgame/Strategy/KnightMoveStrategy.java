package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Knight;
import com.chessgame.Pieces.Piece;

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
