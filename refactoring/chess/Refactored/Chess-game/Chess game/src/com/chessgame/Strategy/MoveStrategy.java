package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Piece;

public interface MoveStrategy {
    boolean canMove(int x, int y, Board board, Piece piece);
}
