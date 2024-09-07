package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.game.Game;
import com.chessgame.pieces.King;
import com.chessgame.pieces.Piece;

public class KingMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int x, int y, Board board, Piece piece) {
    King king = (King) piece;
    int xCord = king.getXcord();
    int yCord = king.getYcord();

    int i = Math.abs(xCord - x);
    int j = Math.abs(yCord - y);

    if( j == 1 && i == 1 || (i+j) == 1) {

      if(board.getPiece(x, y) == null) {
        return true;
      }
      else {
        return board.getPiece(x, y).isWhite() != king.isWhite();				
      }
    }


    king.fetchRook(x, board);
    if(king.getRook() != null && (king.getRook().HasMoved() || king.getHasMoved())) {
      return false;
    }

    else if(king.getRook() != null){

      for(int k=xCord + 1; k<king.getRook().getXcord(); k++) {
        if(board.getPiece(k, yCord) != null) {
          return false;
        }
        for(Movement m: Game.allEnemysMove) {
          if((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
            return false;
          }
        }
      }	
      if(x == king.getRook().getXcord() - 1 && y == yCord) {
        return true;
      }

      for(int k=xCord - 1; k>king.getRook().getXcord(); k--) {
        if(board.getPiece(k, yCord) != null) {
          return false;
        }
        for(Movement m: Game.allEnemysMove) {
          if((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
            return false;
          }
        }
      }
      if(x == king.getRook().getXcord() + 2 && y == yCord) {
        return true;
      }

    }


    return false;

  }



}
