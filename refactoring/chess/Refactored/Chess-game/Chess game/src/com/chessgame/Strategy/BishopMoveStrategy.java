package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Bishop;
import com.chessgame.Pieces.Piece;

public class BishopMoveStrategy implements MoveStrategy {

	@Override
	public boolean canMove(int x, int y, Board board, Piece piece) {
		if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == piece.isWhite()) {
			return false;
		}
	
		if(Math.abs(x-piece.getXcord()) == Math.abs( y-piece.getYcord())) {
            return ((Bishop) piece).bishopMoves(x, y, board);
		}
		return false;
	}

}
