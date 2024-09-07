package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;

public class PawnMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(int positionX, int positionY, Board board, Piece piece) {
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
        if(positionX == leftPawn.getXcord() && positionY == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
          return true;
        }
      }

      if(board.getXY(xCord - 1, yCord) == enpassant) {
        Pawn rightPawn = (Pawn) board.getPiece(xCord - 1, yCord);
        if(positionX == rightPawn.getXcord() && positionY == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
          return true;
        }
      }
    }

    //something blocking the way
    if ((board.getPiece(positionX, positionY) != null && board.getPiece(positionX, positionY).isWhite() == pawn.isWhite())) {
      return false;
    }

    //cant move diagonial if it isnt  for capture
    if (xCord != positionX && board.getPiece(positionX, positionY) == null) {
      return false;
    }

    if (pawn.isWhite()) {
      //move two or 1 square at beggining
      if (pawn.isFirstMove()) {
        if (positionX == xCord && (positionY == yCord - 1 || positionY == yCord - 2) && board.getPiece(positionX, positionY) == null && board.getPiece(positionX, positionY + 1) == null) {
          return true;
        }
      }
      //move forward
      if (positionX == xCord && positionY == yCord - 1 && board.getPiece(positionX, positionY) == null) {
        return true;
      }

      return pawn.capture(positionX, positionY, board);

    }
    if (!pawn.isWhite()) {
      if (pawn.isFirstMove()) {
        if (positionX == xCord && (positionY == yCord + 1 || positionY == yCord + 2) && board.getPiece(positionX, positionY) == null && board.getPiece(positionX, positionY - 1) == null) {
          return true;
        }
      }
      if (positionX == xCord && positionY == yCord + 1 && board.getPiece(positionX, positionY) == null) {
        return true;
      }

      return pawn.capture(positionX, positionY, board);
    }

    return false;
  }

}
