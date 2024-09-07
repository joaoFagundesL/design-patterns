package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;

public class BishopMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int x, int y, Board board, Piece piece) {
    if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == piece.isWhite()) {
      return false;
    }

    if(Math.abs(x-piece.getXcord()) == Math.abs( y-piece.getYcord())) {
      return bishopMoves(x, y, board, piece.getXcord(), piece.getYcord());
    }
    return false;
  }

  private boolean bishopMoves(int x, int y, Board board, int xCord, int yCord) {
    if (x > xCord && y > yCord) {
      return checkDiagonalMove(x, y, 1, 1, board, xCord, yCord);
    } 
    if (x < xCord && y < yCord) {
      return checkDiagonalMove(x, y, -1, -1, board, xCord, yCord);
    }
    if (x > xCord && y < yCord) {
      return checkDiagonalMove(x, y, 1, -1, board, xCord, yCord);
    }
    if (x < xCord && y > yCord) {
      return checkDiagonalMove(x, y, -1, 1, board, xCord, yCord);
    }
    return false;
  }

}
