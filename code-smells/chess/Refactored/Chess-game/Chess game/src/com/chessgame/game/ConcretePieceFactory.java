package com.chessgame.game;

import com.chessgame.board.Board;
import com.chessgame.pieces.Bishop;
import com.chessgame.pieces.King;
import com.chessgame.pieces.Knight;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.Queen;
import com.chessgame.pieces.Rook;

public class ConcretePieceFactory implements PieceFactory {
  @Override
  public Piece createPiece(final char type, final int positionX, final int positionY, final boolean isWhite) {
    Board board = Board.getInstance();
    Piece piece;

    switch (Character.toUpperCase(type)) {
      case 'R':
        piece = new Rook(positionX, positionY, isWhite, board, isWhite ? 5 : -5);
        break;
      case 'N':
        piece = new Knight(positionX, positionY, isWhite, board, isWhite ? 3 : -3);
        break;
      case 'B':
        piece = new Bishop(positionX, positionY, isWhite, board, isWhite ? 3 : -3);
        break;
      case 'Q':
        piece = new Queen(positionX, positionY, isWhite, board, isWhite ? 8 : -8);
        break;
      case 'K':
        piece = new King(positionX, positionY, isWhite, board, isWhite ? 10 : -10);
        if (isWhite) {
          Game.wk = (King) piece;
        } else {
          Game.bk = (King) piece;
        }
        break;
      case 'P':
        piece = new Pawn(positionX, positionY, isWhite, board, isWhite ? 1 : -1);
        break;
      default:
        throw new IllegalArgumentException("Invalid piece type: " + type);
    }
    
    return piece;
  }
}