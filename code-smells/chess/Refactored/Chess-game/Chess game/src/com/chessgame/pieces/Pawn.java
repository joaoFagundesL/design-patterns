package com.chessgame.pieces;
import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.game.Game;
import com.chessgame.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
  private boolean firstMove;
  private boolean moved2Squares = false;

  public Pawn(int x, int y, boolean iswhite, Board board, int value) {
    super(x, y, iswhite, board, value, new PawnMoveStrategy());
    firstMove = true;
    this.pieceImage = PieceImages.PAWN;
  }

  public void intializeSide(int value){
    super.intializeSide(value);
    if(isWhite()) {
      image = PieceImages.wp;
    }
    else {
      image = PieceImages.bp;
    }
  }

  @Override

  public boolean makeMove(int toX, int toY, Board board) {
    Movement move = new Movement(xCord, yCord, toX, toY, this);
    if(!alive()) {
      return false;
    }
    if(moves.contains(move)) {

      if(toX == xCord + 1 && yCord -(isWhite ? 1 : -1) == toY) {
        if(board.getXY(toX, toY) == 0) {
          Game.AllPieces.remove(board.getPiece(xCord + 1, yCord));
          Game.fillPieces();
          board.setXY(xCord + 1, yCord, 0);
          board.setPieceIntoBoard(xCord + 1, yCord, null);
        }
      }

      if(toX == xCord -1 && yCord -(isWhite ? 1 : -1) == toY) {
        if(board.getXY(toX, toY) == 0) {
          Game.AllPieces.remove(board.getPiece(xCord - 1, yCord));
          Game.fillPieces();
          board.setXY(xCord -1, yCord, 0);
          board.setPieceIntoBoard(xCord - 1, yCord, null);
        }
      }


      if(firstMove && Math.abs((yCord - toY)) == 2){
        moved2Squares = true;
      }
      removeEnpassant();
      board.updatePieces(xCord, yCord, toX, toY,this);
      xCord = toX;
      yCord = toY;	
      firstMove = false;
      return true;
    }

    return false;

  }

  private void removeEnpassant() {
    for(Piece p: Game.AllPieces) {
      if(p instanceof Pawn && p != this) {
      ((Pawn) p ).setMoved2Squares(false);
      }
    }
  }

  public boolean madeToTheEnd() {
    if(isWhite && yCord == 0) {
      return true;
    }

    if(!isWhite && yCord == 7) {
      return true;
    }
    return false;
  }



  public boolean capture(int x, int y, Board board) {
    if(isWhite()) {
      //capture
      if (y == yCord - 1 && x == xCord + 1) {
        return true;
      }
      //capture
      if (y == yCord - 1 && x == xCord - 1) {
        return true;
      }
    }
    else {
      if (y == yCord + 1 && x == xCord + 1) {
        return true;
      }
      if (y == yCord + 1 && x == xCord - 1) {
        return true;
      }
    }
    return false;
  }

  public void removeEnpassanCapturedpiece(int x, int y) {

  }

  public boolean isFirstMove() {
    return firstMove;
  }

  public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
  }

  public boolean isMoved2Squares() {
    return moved2Squares;
  }

  public void setMoved2Squares(boolean moved2Squares) {
    this.moved2Squares = moved2Squares;
  }



}
