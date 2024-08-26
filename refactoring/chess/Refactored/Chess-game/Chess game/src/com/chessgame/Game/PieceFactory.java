package com.chessgame.Game;

import com.chessgame.Pieces.Piece;

public interface PieceFactory {
  Piece createPiece(char type, int x, int y, boolean isWhite);
}
