package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;

public class PawnMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int x, int y, Board board, Piece piece) {
    Pawn pawn = (Pawn) piece;

    int xCord = pawn.getXcord();
    int yCord = pawn.getYcord();

    int enpassant = 0;

    if(pawn.isWhite()) {
      enpassant = -1;
    }
    else {
      enpassant = 1;
    }

    if(xCord > 0 && xCord  < 7) {
      if(board.getXY(xCord + 1, yCord) == enpassant) {
        Pawn leftPawn = (Pawn) board.getPiece(xCord + 1, yCord);
        if(x == leftPawn.getXcord() && y == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
          return true;
        }
      }

      if(board.getXY(xCord - 1, yCord) == enpassant) {
        Pawn rightPawn = (Pawn) board.getPiece(xCord - 1, yCord);
        if(x == rightPawn.getXcord() && y == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
          return true;
        }
      }
    }

    //something blocking the way
    if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == pawn.isWhite())) {
      return false;
    }

    //cant move diagonial if it isnt  for capture
    if (xCord != x && board.getPiece(x, y) == null) {
      return false;
    }

    if (pawn.isWhite()) {
      //move two or 1 square at beggining
      if (pawn.isFirstMove()) {
        if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getPiece(x, y) == null && board.getPiece(x, y + 1) == null) {
          return true;
        }
      }
      //move forward
      if (x == xCord && y == yCord - 1 && board.getPiece(x, y) == null) {
        return true;
      }

      return pawn.capture(x, y, board);

    }
    if (!pawn.isWhite()) {
      if (pawn.isFirstMove()) {
        if (x == xCord && (y == yCord + 1 || y == yCord + 2) && board.getPiece(x, y) == null && board.getPiece(x, y - 1) == null) {
          return true;
        }
      }
      if (x == xCord && y == yCord + 1 && board.getPiece(x, y) == null) {
        return true;
      }

      return pawn.capture(x, y, board);
    }

    return false;
  }

}
