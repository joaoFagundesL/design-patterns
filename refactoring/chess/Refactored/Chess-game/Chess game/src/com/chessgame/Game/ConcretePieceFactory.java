package com.chessgame.Game;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Bishop;
import com.chessgame.Pieces.King;
import com.chessgame.Pieces.Knight;
import com.chessgame.Pieces.Pawn;
import com.chessgame.Pieces.Piece;
import com.chessgame.Pieces.Queen;
import com.chessgame.Pieces.Rook;

public class ConcretePieceFactory implements PieceFactory {
  @Override
  public Piece createPiece(char type, int x, int y, boolean isWhite) {
    Board board = Board.getInstance();
    switch (Character.toUpperCase(type)) {
      case 'R': return new Rook(x, y, isWhite, board, isWhite ? 5 : -5);
      case 'N': return new Knight(x, y, isWhite, board, isWhite ? 3 : -3);
      case 'B': return new Bishop(x, y, isWhite, board, isWhite ? 3 : -3);
      case 'Q': return new Queen(x, y, isWhite, board, isWhite ? 8 : -8);
      case 'K':
      King king = new King(x, y, isWhite, board, isWhite ? 10 : -10);
      if (isWhite) {
        Game.wk = king;
      } else {
        Game.bk = king;
      }
      return king;
      case 'P': return new Pawn(x, y, isWhite, board, isWhite ? 1 : -1);
      default: throw new IllegalArgumentException("Invalid piece type: " + type);
    }
  }
}
