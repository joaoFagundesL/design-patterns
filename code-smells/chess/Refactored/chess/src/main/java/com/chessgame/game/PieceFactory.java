package com.chessgame.game;

import com.chessgame.pieces.Piece;

public interface PieceFactory {
  Piece createPiece(char type, int x, int y, boolean isWhite);
}
